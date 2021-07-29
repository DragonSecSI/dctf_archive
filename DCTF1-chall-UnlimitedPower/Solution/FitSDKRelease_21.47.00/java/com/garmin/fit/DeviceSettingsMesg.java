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


package com.garmin.fit;
import java.math.BigInteger;


public class DeviceSettingsMesg extends Mesg {

    
    public static final int ActiveTimeZoneFieldNum = 0;
    
    public static final int UtcOffsetFieldNum = 1;
    
    public static final int TimeOffsetFieldNum = 2;
    
    public static final int TimeModeFieldNum = 4;
    
    public static final int TimeZoneOffsetFieldNum = 5;
    
    public static final int BacklightModeFieldNum = 12;
    
    public static final int ActivityTrackerEnabledFieldNum = 36;
    
    public static final int ClockTimeFieldNum = 39;
    
    public static final int PagesEnabledFieldNum = 40;
    
    public static final int MoveAlertEnabledFieldNum = 46;
    
    public static final int DateModeFieldNum = 47;
    
    public static final int DisplayOrientationFieldNum = 55;
    
    public static final int MountingSideFieldNum = 56;
    
    public static final int DefaultPageFieldNum = 57;
    
    public static final int AutosyncMinStepsFieldNum = 58;
    
    public static final int AutosyncMinTimeFieldNum = 59;
    
    public static final int LactateThresholdAutodetectEnabledFieldNum = 80;
    
    public static final int BleAutoUploadEnabledFieldNum = 86;
    
    public static final int AutoSyncFrequencyFieldNum = 89;
    
    public static final int AutoActivityDetectFieldNum = 90;
    
    public static final int NumberOfScreensFieldNum = 94;
    
    public static final int SmartNotificationDisplayOrientationFieldNum = 95;
    
    public static final int TapInterfaceFieldNum = 134;
    
    public static final int TapSensitivityFieldNum = 174;
    

    protected static final  Mesg deviceSettingsMesg;
    static {
        // device_settings
        deviceSettingsMesg = new Mesg("device_settings", MesgNum.DEVICE_SETTINGS);
        deviceSettingsMesg.addField(new Field("active_time_zone", ActiveTimeZoneFieldNum, 2, 1, 0, "", false, Profile.Type.UINT8));
        
        deviceSettingsMesg.addField(new Field("utc_offset", UtcOffsetFieldNum, 134, 1, 0, "", false, Profile.Type.UINT32));
        
        deviceSettingsMesg.addField(new Field("time_offset", TimeOffsetFieldNum, 134, 1, 0, "s", false, Profile.Type.UINT32));
        
        deviceSettingsMesg.addField(new Field("time_mode", TimeModeFieldNum, 0, 1, 0, "", false, Profile.Type.TIME_MODE));
        
        deviceSettingsMesg.addField(new Field("time_zone_offset", TimeZoneOffsetFieldNum, 1, 4, 0, "hr", false, Profile.Type.SINT8));
        
        deviceSettingsMesg.addField(new Field("backlight_mode", BacklightModeFieldNum, 0, 1, 0, "", false, Profile.Type.BACKLIGHT_MODE));
        
        deviceSettingsMesg.addField(new Field("activity_tracker_enabled", ActivityTrackerEnabledFieldNum, 0, 1, 0, "", false, Profile.Type.BOOL));
        
        deviceSettingsMesg.addField(new Field("clock_time", ClockTimeFieldNum, 134, 1, 0, "", false, Profile.Type.DATE_TIME));
        
        deviceSettingsMesg.addField(new Field("pages_enabled", PagesEnabledFieldNum, 132, 1, 0, "", false, Profile.Type.UINT16));
        
        deviceSettingsMesg.addField(new Field("move_alert_enabled", MoveAlertEnabledFieldNum, 0, 1, 0, "", false, Profile.Type.BOOL));
        
        deviceSettingsMesg.addField(new Field("date_mode", DateModeFieldNum, 0, 1, 0, "", false, Profile.Type.DATE_MODE));
        
        deviceSettingsMesg.addField(new Field("display_orientation", DisplayOrientationFieldNum, 0, 1, 0, "", false, Profile.Type.DISPLAY_ORIENTATION));
        
        deviceSettingsMesg.addField(new Field("mounting_side", MountingSideFieldNum, 0, 1, 0, "", false, Profile.Type.SIDE));
        
        deviceSettingsMesg.addField(new Field("default_page", DefaultPageFieldNum, 132, 1, 0, "", false, Profile.Type.UINT16));
        
        deviceSettingsMesg.addField(new Field("autosync_min_steps", AutosyncMinStepsFieldNum, 132, 1, 0, "steps", false, Profile.Type.UINT16));
        
        deviceSettingsMesg.addField(new Field("autosync_min_time", AutosyncMinTimeFieldNum, 132, 1, 0, "minutes", false, Profile.Type.UINT16));
        
        deviceSettingsMesg.addField(new Field("lactate_threshold_autodetect_enabled", LactateThresholdAutodetectEnabledFieldNum, 0, 1, 0, "", false, Profile.Type.BOOL));
        
        deviceSettingsMesg.addField(new Field("ble_auto_upload_enabled", BleAutoUploadEnabledFieldNum, 0, 1, 0, "", false, Profile.Type.BOOL));
        
        deviceSettingsMesg.addField(new Field("auto_sync_frequency", AutoSyncFrequencyFieldNum, 0, 1, 0, "", false, Profile.Type.AUTO_SYNC_FREQUENCY));
        
        deviceSettingsMesg.addField(new Field("auto_activity_detect", AutoActivityDetectFieldNum, 134, 1, 0, "", false, Profile.Type.AUTO_ACTIVITY_DETECT));
        
        deviceSettingsMesg.addField(new Field("number_of_screens", NumberOfScreensFieldNum, 2, 1, 0, "", false, Profile.Type.UINT8));
        
        deviceSettingsMesg.addField(new Field("smart_notification_display_orientation", SmartNotificationDisplayOrientationFieldNum, 0, 1, 0, "", false, Profile.Type.DISPLAY_ORIENTATION));
        
        deviceSettingsMesg.addField(new Field("tap_interface", TapInterfaceFieldNum, 0, 1, 0, "", false, Profile.Type.SWITCH));
        
        deviceSettingsMesg.addField(new Field("tap_sensitivity", TapSensitivityFieldNum, 0, 1, 1, "", false, Profile.Type.TAP_SENSITIVITY));
        
    }

    public DeviceSettingsMesg() {
        super(Factory.createMesg(MesgNum.DEVICE_SETTINGS));
    }

    public DeviceSettingsMesg(final Mesg mesg) {
        super(mesg);
    }


    /**
     * Get active_time_zone field
     * Comment: Index into time zone arrays.
     *
     * @return active_time_zone
     */
    public Short getActiveTimeZone() {
        return getFieldShortValue(0, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set active_time_zone field
     * Comment: Index into time zone arrays.
     *
     * @param activeTimeZone
     */
    public void setActiveTimeZone(Short activeTimeZone) {
        setFieldValue(0, 0, activeTimeZone, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get utc_offset field
     * Comment: Offset from system time. Required to convert timestamp from system time to UTC.
     *
     * @return utc_offset
     */
    public Long getUtcOffset() {
        return getFieldLongValue(1, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set utc_offset field
     * Comment: Offset from system time. Required to convert timestamp from system time to UTC.
     *
     * @param utcOffset
     */
    public void setUtcOffset(Long utcOffset) {
        setFieldValue(1, 0, utcOffset, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public Long[] getTimeOffset() {
        
        return getFieldLongValues(2, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        
    }

    /**
     * @return number of time_offset
     */
    public int getNumTimeOffset() {
        return getNumFieldValues(2, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get time_offset field
     * Units: s
     * Comment: Offset from system time.
     *
     * @param index of time_offset
     * @return time_offset
     */
    public Long getTimeOffset(int index) {
        return getFieldLongValue(2, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set time_offset field
     * Units: s
     * Comment: Offset from system time.
     *
     * @param index of time_offset
     * @param timeOffset
     */
    public void setTimeOffset(int index, Long timeOffset) {
        setFieldValue(2, index, timeOffset, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public TimeMode[] getTimeMode() {
        
        Short[] values = getFieldShortValues(4, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        TimeMode[] rv = new TimeMode[values.length];
        for(int i = 0; i < values.length; i++){
            rv[i] = TimeMode.getByValue(values[i]);
        }
        return rv;
        
    }

    /**
     * @return number of time_mode
     */
    public int getNumTimeMode() {
        return getNumFieldValues(4, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get time_mode field
     * Comment: Display mode for the time
     *
     * @param index of time_mode
     * @return time_mode
     */
    public TimeMode getTimeMode(int index) {
        Short value = getFieldShortValue(4, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return TimeMode.getByValue(value);
    }

    /**
     * Set time_mode field
     * Comment: Display mode for the time
     *
     * @param index of time_mode
     * @param timeMode
     */
    public void setTimeMode(int index, TimeMode timeMode) {
        setFieldValue(4, index, timeMode.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public Float[] getTimeZoneOffset() {
        
        return getFieldFloatValues(5, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        
    }

    /**
     * @return number of time_zone_offset
     */
    public int getNumTimeZoneOffset() {
        return getNumFieldValues(5, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get time_zone_offset field
     * Units: hr
     * Comment: timezone offset in 1/4 hour increments
     *
     * @param index of time_zone_offset
     * @return time_zone_offset
     */
    public Float getTimeZoneOffset(int index) {
        return getFieldFloatValue(5, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set time_zone_offset field
     * Units: hr
     * Comment: timezone offset in 1/4 hour increments
     *
     * @param index of time_zone_offset
     * @param timeZoneOffset
     */
    public void setTimeZoneOffset(int index, Float timeZoneOffset) {
        setFieldValue(5, index, timeZoneOffset, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get backlight_mode field
     * Comment: Mode for backlight
     *
     * @return backlight_mode
     */
    public BacklightMode getBacklightMode() {
        Short value = getFieldShortValue(12, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return BacklightMode.getByValue(value);
    }

    /**
     * Set backlight_mode field
     * Comment: Mode for backlight
     *
     * @param backlightMode
     */
    public void setBacklightMode(BacklightMode backlightMode) {
        setFieldValue(12, 0, backlightMode.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get activity_tracker_enabled field
     * Comment: Enabled state of the activity tracker functionality
     *
     * @return activity_tracker_enabled
     */
    public Bool getActivityTrackerEnabled() {
        Short value = getFieldShortValue(36, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Bool.getByValue(value);
    }

    /**
     * Set activity_tracker_enabled field
     * Comment: Enabled state of the activity tracker functionality
     *
     * @param activityTrackerEnabled
     */
    public void setActivityTrackerEnabled(Bool activityTrackerEnabled) {
        setFieldValue(36, 0, activityTrackerEnabled.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get clock_time field
     * Comment: UTC timestamp used to set the devices clock and date
     *
     * @return clock_time
     */
    public DateTime getClockTime() {
        return timestampToDateTime(getFieldLongValue(39, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD));
    }

    /**
     * Set clock_time field
     * Comment: UTC timestamp used to set the devices clock and date
     *
     * @param clockTime
     */
    public void setClockTime(DateTime clockTime) {
        setFieldValue(39, 0, clockTime.getTimestamp(), Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public Integer[] getPagesEnabled() {
        
        return getFieldIntegerValues(40, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        
    }

    /**
     * @return number of pages_enabled
     */
    public int getNumPagesEnabled() {
        return getNumFieldValues(40, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get pages_enabled field
     * Comment: Bitfield  to configure enabled screens for each supported loop
     *
     * @param index of pages_enabled
     * @return pages_enabled
     */
    public Integer getPagesEnabled(int index) {
        return getFieldIntegerValue(40, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set pages_enabled field
     * Comment: Bitfield  to configure enabled screens for each supported loop
     *
     * @param index of pages_enabled
     * @param pagesEnabled
     */
    public void setPagesEnabled(int index, Integer pagesEnabled) {
        setFieldValue(40, index, pagesEnabled, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get move_alert_enabled field
     * Comment: Enabled state of the move alert
     *
     * @return move_alert_enabled
     */
    public Bool getMoveAlertEnabled() {
        Short value = getFieldShortValue(46, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Bool.getByValue(value);
    }

    /**
     * Set move_alert_enabled field
     * Comment: Enabled state of the move alert
     *
     * @param moveAlertEnabled
     */
    public void setMoveAlertEnabled(Bool moveAlertEnabled) {
        setFieldValue(46, 0, moveAlertEnabled.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get date_mode field
     * Comment: Display mode for the date
     *
     * @return date_mode
     */
    public DateMode getDateMode() {
        Short value = getFieldShortValue(47, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return DateMode.getByValue(value);
    }

    /**
     * Set date_mode field
     * Comment: Display mode for the date
     *
     * @param dateMode
     */
    public void setDateMode(DateMode dateMode) {
        setFieldValue(47, 0, dateMode.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get display_orientation field
     *
     * @return display_orientation
     */
    public DisplayOrientation getDisplayOrientation() {
        Short value = getFieldShortValue(55, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return DisplayOrientation.getByValue(value);
    }

    /**
     * Set display_orientation field
     *
     * @param displayOrientation
     */
    public void setDisplayOrientation(DisplayOrientation displayOrientation) {
        setFieldValue(55, 0, displayOrientation.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get mounting_side field
     *
     * @return mounting_side
     */
    public Side getMountingSide() {
        Short value = getFieldShortValue(56, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Side.getByValue(value);
    }

    /**
     * Set mounting_side field
     *
     * @param mountingSide
     */
    public void setMountingSide(Side mountingSide) {
        setFieldValue(56, 0, mountingSide.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public Integer[] getDefaultPage() {
        
        return getFieldIntegerValues(57, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        
    }

    /**
     * @return number of default_page
     */
    public int getNumDefaultPage() {
        return getNumFieldValues(57, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get default_page field
     * Comment: Bitfield to indicate one page as default for each supported loop
     *
     * @param index of default_page
     * @return default_page
     */
    public Integer getDefaultPage(int index) {
        return getFieldIntegerValue(57, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set default_page field
     * Comment: Bitfield to indicate one page as default for each supported loop
     *
     * @param index of default_page
     * @param defaultPage
     */
    public void setDefaultPage(int index, Integer defaultPage) {
        setFieldValue(57, index, defaultPage, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get autosync_min_steps field
     * Units: steps
     * Comment: Minimum steps before an autosync can occur
     *
     * @return autosync_min_steps
     */
    public Integer getAutosyncMinSteps() {
        return getFieldIntegerValue(58, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set autosync_min_steps field
     * Units: steps
     * Comment: Minimum steps before an autosync can occur
     *
     * @param autosyncMinSteps
     */
    public void setAutosyncMinSteps(Integer autosyncMinSteps) {
        setFieldValue(58, 0, autosyncMinSteps, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get autosync_min_time field
     * Units: minutes
     * Comment: Minimum minutes before an autosync can occur
     *
     * @return autosync_min_time
     */
    public Integer getAutosyncMinTime() {
        return getFieldIntegerValue(59, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set autosync_min_time field
     * Units: minutes
     * Comment: Minimum minutes before an autosync can occur
     *
     * @param autosyncMinTime
     */
    public void setAutosyncMinTime(Integer autosyncMinTime) {
        setFieldValue(59, 0, autosyncMinTime, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get lactate_threshold_autodetect_enabled field
     * Comment: Enable auto-detect setting for the lactate threshold feature.
     *
     * @return lactate_threshold_autodetect_enabled
     */
    public Bool getLactateThresholdAutodetectEnabled() {
        Short value = getFieldShortValue(80, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Bool.getByValue(value);
    }

    /**
     * Set lactate_threshold_autodetect_enabled field
     * Comment: Enable auto-detect setting for the lactate threshold feature.
     *
     * @param lactateThresholdAutodetectEnabled
     */
    public void setLactateThresholdAutodetectEnabled(Bool lactateThresholdAutodetectEnabled) {
        setFieldValue(80, 0, lactateThresholdAutodetectEnabled.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get ble_auto_upload_enabled field
     * Comment: Automatically upload using BLE
     *
     * @return ble_auto_upload_enabled
     */
    public Bool getBleAutoUploadEnabled() {
        Short value = getFieldShortValue(86, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Bool.getByValue(value);
    }

    /**
     * Set ble_auto_upload_enabled field
     * Comment: Automatically upload using BLE
     *
     * @param bleAutoUploadEnabled
     */
    public void setBleAutoUploadEnabled(Bool bleAutoUploadEnabled) {
        setFieldValue(86, 0, bleAutoUploadEnabled.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get auto_sync_frequency field
     * Comment: Helps to conserve battery by changing modes
     *
     * @return auto_sync_frequency
     */
    public AutoSyncFrequency getAutoSyncFrequency() {
        Short value = getFieldShortValue(89, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return AutoSyncFrequency.getByValue(value);
    }

    /**
     * Set auto_sync_frequency field
     * Comment: Helps to conserve battery by changing modes
     *
     * @param autoSyncFrequency
     */
    public void setAutoSyncFrequency(AutoSyncFrequency autoSyncFrequency) {
        setFieldValue(89, 0, autoSyncFrequency.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get auto_activity_detect field
     * Comment: Allows setting specific activities auto-activity detect enabled/disabled settings
     *
     * @return auto_activity_detect
     */
    public Long getAutoActivityDetect() {
        return getFieldLongValue(90, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set auto_activity_detect field
     * Comment: Allows setting specific activities auto-activity detect enabled/disabled settings
     *
     * @param autoActivityDetect
     */
    public void setAutoActivityDetect(Long autoActivityDetect) {
        setFieldValue(90, 0, autoActivityDetect, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get number_of_screens field
     * Comment: Number of screens configured to display
     *
     * @return number_of_screens
     */
    public Short getNumberOfScreens() {
        return getFieldShortValue(94, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set number_of_screens field
     * Comment: Number of screens configured to display
     *
     * @param numberOfScreens
     */
    public void setNumberOfScreens(Short numberOfScreens) {
        setFieldValue(94, 0, numberOfScreens, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get smart_notification_display_orientation field
     * Comment: Smart Notification display orientation
     *
     * @return smart_notification_display_orientation
     */
    public DisplayOrientation getSmartNotificationDisplayOrientation() {
        Short value = getFieldShortValue(95, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return DisplayOrientation.getByValue(value);
    }

    /**
     * Set smart_notification_display_orientation field
     * Comment: Smart Notification display orientation
     *
     * @param smartNotificationDisplayOrientation
     */
    public void setSmartNotificationDisplayOrientation(DisplayOrientation smartNotificationDisplayOrientation) {
        setFieldValue(95, 0, smartNotificationDisplayOrientation.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get tap_interface field
     *
     * @return tap_interface
     */
    public Switch getTapInterface() {
        Short value = getFieldShortValue(134, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Switch.getByValue(value);
    }

    /**
     * Set tap_interface field
     *
     * @param tapInterface
     */
    public void setTapInterface(Switch tapInterface) {
        setFieldValue(134, 0, tapInterface.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get tap_sensitivity field
     * Comment: Used to hold the tap threshold setting
     *
     * @return tap_sensitivity
     */
    public TapSensitivity getTapSensitivity() {
        Short value = getFieldShortValue(174, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return TapSensitivity.getByValue(value);
    }

    /**
     * Set tap_sensitivity field
     * Comment: Used to hold the tap threshold setting
     *
     * @param tapSensitivity
     */
    public void setTapSensitivity(TapSensitivity tapSensitivity) {
        setFieldValue(174, 0, tapSensitivity.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

}
