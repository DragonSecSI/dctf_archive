////////////////////////////////////////////////////////////////////////////////
// The following FIT Protocol software provided may be used with FIT protocol
// devices only and remains the copyrighted property of Garmin Canada Inc.
// The software is being provided on an "as-is" basis and as an accommodation,
// and therefore all warranties, representations, or guarantees of any kind
// (whether express, implied or statutory) including, without limitation,
// warranties of merchantability, non-infringement, or fitness for a particular
// purpose, are specifically disclaimed.
//
// Copyright 2021 Garmin Canada Inc.
////////////////////////////////////////////////////////////////////////////////
// ****WARNING****  This file is auto-generated!  Do NOT edit this file.
// Profile Version = 21.47Release
// Tag = production/akw/21.47.00-0-geec27411
////////////////////////////////////////////////////////////////////////////////


package com.garmin.fit.plugins;

import java.util.List;

import com.garmin.fit.*;

/**
 * Provides functionality to adjust uncalibrated data from 3D sensors such as gyroscopes (gyroscope_data)
 * and accelerometers (accelerometer_data) using calibration parameters from three_d_sensor_calibration
 * messages encountered.  Adjusted data is added as a new field to the existing 3d sensor data messages.
 *
 * Requirements for correct operation:
 *    - 3D sensor data
 *    - At least one three_d_sensor_calibration message for each sensor type
 *    - If more than one three_d_sensor_calibration message is encountered the last one is used
 *
 */
public class ThreeDSensorAdjustmentPlugin implements MesgBroadcastPlugin {

    private final int X_AXIS_OFFSET = 0;
    private final int Y_AXIS_OFFSET = 1;
    private final int Z_AXIS_OFFSET = 2;
    private final int NUM_AXIS = 3;

    private class CalibrationParameters {
        long calFactor;
        long calDivisor;
        long[] channelOffset = new long[3];
        long levelShift;
        float[][] rotationMatrix = new float[3][3];

        public void LoadParams(ThreeDSensorCalibrationMesg calMesg) {
            this.calFactor = calMesg.getCalibrationFactor();
            this.calDivisor = calMesg.getCalibrationDivisor();
            this.levelShift = calMesg.getLevelShift();

            this.channelOffset[X_AXIS_OFFSET] = calMesg.getOffsetCal(X_AXIS_OFFSET);
            this.channelOffset[Y_AXIS_OFFSET] = calMesg.getOffsetCal(Y_AXIS_OFFSET);
            this.channelOffset[Z_AXIS_OFFSET] = calMesg.getOffsetCal(Z_AXIS_OFFSET);

            // rotationMatrix is row major
            this.rotationMatrix[0][0] = calMesg.getOrientationMatrix(0);
            this.rotationMatrix[0][1] = calMesg.getOrientationMatrix(1);
            this.rotationMatrix[0][2] = calMesg.getOrientationMatrix(2);
            this.rotationMatrix[1][0] = calMesg.getOrientationMatrix(3);
            this.rotationMatrix[1][1] = calMesg.getOrientationMatrix(4);
            this.rotationMatrix[1][2] = calMesg.getOrientationMatrix(5);
            this.rotationMatrix[2][0] = calMesg.getOrientationMatrix(6);
            this.rotationMatrix[2][1] = calMesg.getOrientationMatrix(7);
            this.rotationMatrix[2][2] = calMesg.getOrientationMatrix(8);
        }

    }

    boolean haveGyroCal = false;
    boolean haveAccelCal = false;
    boolean haveMagCal = false;
    CalibrationParameters gyroCalParams = new CalibrationParameters();
    CalibrationParameters accelCalParams = new CalibrationParameters();
    CalibrationParameters magCalParams = new CalibrationParameters();

    /**
     * Peeks messages as they are being added to the buffer
     *
     * @param mesg the message that has just been buffered by BufferedMesgBroadcaster
     */
    public void onIncomingMesg(final Mesg mesg) {
        switch (mesg.getNum()) {

            // Capture the calibration parameters
        case MesgNum.THREE_D_SENSOR_CALIBRATION:
            ThreeDSensorCalibrationMesg calMesg = new ThreeDSensorCalibrationMesg(mesg);

            switch(calMesg.getSensorType()) {
            case ACCELEROMETER:
                accelCalParams.LoadParams(calMesg);
                haveAccelCal = true;
                break;
            case GYROSCOPE:
                gyroCalParams.LoadParams(calMesg);
                haveGyroCal = true;
                break;
            case COMPASS:
                magCalParams.LoadParams(calMesg);
                haveMagCal = true;
                break;
            default:
                break;
            }
            break;

        default:
            break;

        } // switch
    }

    /**
     * Detects 3D Sensor messages and adjusts the raw data if calibration is available.
     * New fields are added to contain the adjusted sensor data.
     *
     * @param mesgs the message list that is about to be broadcast to all MesgListeners.  \
     Note: The List is 'final' but the references within the list are not, \
     therefore editing Mesg objects within mesgs will alter the messages   \
     that are broadcast to listeners.
     *
     * DO NOT add or remove any messages to mesgs
     */
    public void onBroadcast(final List<Mesg> mesgs) {
        int[] rawXYZ = new int[NUM_AXIS];
        float[] calibratedXYZ;
        int count;

        for (Mesg mesg: mesgs) {
            switch(mesg.getNum()) {
            case MesgNum.ACCELEROMETER_DATA:
                if (haveAccelCal) {
                    AccelerometerDataMesg accelData = new AccelerometerDataMesg(mesg);
                    count = accelData.getNumAccelX();
                    for (int i=0; i<count; i++) {
                        // Extract the uncalibrated accel data from incoming message
                        rawXYZ[X_AXIS_OFFSET] = accelData.getAccelX(i).intValue();
                        rawXYZ[Y_AXIS_OFFSET] = accelData.getAccelY(i).intValue();
                        rawXYZ[Z_AXIS_OFFSET] = accelData.getAccelZ(i).intValue();
                        // Apply calibration to the values
                        calibratedXYZ = adjustSensorData(rawXYZ, accelCalParams);
                        // Now update the message
                        processCalibrationFactor(mesg, new String[]{ "calibrated_accel_x", "calibrated_accel_y", "calibrated_accel_z" }, calibratedXYZ, "accelerometer_data");
                    }
                }
                break;
            case MesgNum.GYROSCOPE_DATA:
                if (haveGyroCal) {
                    GyroscopeDataMesg gyroData = new GyroscopeDataMesg(mesg);
                    count = gyroData.getNumGyroX();
                    for (int i=0; i<count; i++) {
                        // Extract the uncalibrated gyro data from incoming message
                        rawXYZ[X_AXIS_OFFSET] = gyroData.getGyroX(i).intValue();
                        rawXYZ[Y_AXIS_OFFSET] = gyroData.getGyroY(i).intValue();
                        rawXYZ[Z_AXIS_OFFSET] = gyroData.getGyroZ(i).intValue();
                        // Apply calibration
                        calibratedXYZ = adjustSensorData(rawXYZ, gyroCalParams);
                        // Add calibrated data fields to existing message if they are not already present
                        processCalibrationFactor(mesg, new String[]{ "calibrated_gyro_x", "calibrated_gyro_y", "calibrated_gyro_z" }, calibratedXYZ, "gyroscope_data");
                    }
                }
                break;
            case MesgNum.MAGNETOMETER_DATA:
                if (haveMagCal) {
                    MagnetometerDataMesg magData = new MagnetometerDataMesg(mesg);
                    count = magData.getNumMagX();
                    for (int i=0; i<count; i++) {
                        // Extract the uncalibrated gyro data from incoming message
                        rawXYZ[X_AXIS_OFFSET] = magData.getMagX(i).intValue();
                        rawXYZ[Y_AXIS_OFFSET] = magData.getMagY(i).intValue();
                        rawXYZ[Z_AXIS_OFFSET] = magData.getMagZ(i).intValue();
                        // Apply calibration
                        calibratedXYZ = adjustSensorData(rawXYZ, magCalParams);
                        // Add calibrated data fields to existing message if they are not already present
                        processCalibrationFactor(mesg, new String[]{ "calibrated_mag_x", "calibrated_mag_y", "calibrated_mag_z" }, calibratedXYZ, "magnetometer_data");
                    }
                }
                break;
            default:
                break;
            }
        }
    }

    private void processCalibrationFactor(Mesg mesg, final String[] fieldsXYZ, final float[] calibratedXYZ, String mesgName) {
        if ((fieldsXYZ.length != NUM_AXIS) || (calibratedXYZ.length != NUM_AXIS)) {
            //Invalid number of arguments
            return;
        }
        // Add calibrated data fields to existing message if they are not already present
        if (mesg.getField(fieldsXYZ[X_AXIS_OFFSET]) == null) {
            mesg.addField(Factory.createField(mesgName, fieldsXYZ[X_AXIS_OFFSET]));
        }
        if (mesg.getField(fieldsXYZ[Y_AXIS_OFFSET]) == null) {
            mesg.addField(Factory.createField(mesgName, fieldsXYZ[Y_AXIS_OFFSET]));
        }
        if (mesg.getField(fieldsXYZ[Z_AXIS_OFFSET]) == null) {
            mesg.addField(Factory.createField(mesgName, fieldsXYZ[Z_AXIS_OFFSET]));
        }
        mesg.getField(fieldsXYZ[X_AXIS_OFFSET]).addValue(calibratedXYZ[X_AXIS_OFFSET]);
        mesg.getField(fieldsXYZ[Y_AXIS_OFFSET]).addValue(calibratedXYZ[Y_AXIS_OFFSET]);
        mesg.getField(fieldsXYZ[Z_AXIS_OFFSET]).addValue(calibratedXYZ[Z_AXIS_OFFSET]);
    }

    private float[] adjustSensorData(int rawData[], CalibrationParameters calParams) {
        float[] calibratedValues = new float[rawData.length];
        float[] rotatedValues = new float[rawData.length];

        // Apply the calibration parameters
        for (int i=0; i<rawData.length; i++) {
            calibratedValues[i] = (float)rawData[i];
            calibratedValues[i] -= calParams.levelShift;
            calibratedValues[i] -= calParams.channelOffset[i];
            calibratedValues[i] *= calParams.calFactor;
            calibratedValues[i] /= calParams.calDivisor;
        }
        // Apply the rotation matrix
        // [Rotation] * [XYZ]  See FIT-1063
        rotatedValues[0] = calParams.rotationMatrix[0][0]*calibratedValues[0] + calParams.rotationMatrix[0][1]*calibratedValues[1] + calParams.rotationMatrix[0][2]*calibratedValues[2];
        rotatedValues[1] = calParams.rotationMatrix[1][0]*calibratedValues[0] + calParams.rotationMatrix[1][1]*calibratedValues[1] + calParams.rotationMatrix[1][2]*calibratedValues[2];
        rotatedValues[2] = calParams.rotationMatrix[2][0]*calibratedValues[0] + calParams.rotationMatrix[2][1]*calibratedValues[1] + calParams.rotationMatrix[2][2]*calibratedValues[2];

        return rotatedValues;
    }
}
