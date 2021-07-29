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


public class CourseMesg extends Mesg {

    
    public static final int SportFieldNum = 4;
    
    public static final int NameFieldNum = 5;
    
    public static final int CapabilitiesFieldNum = 6;
    
    public static final int SubSportFieldNum = 7;
    

    protected static final  Mesg courseMesg;
    static {
        // course
        courseMesg = new Mesg("course", MesgNum.COURSE);
        courseMesg.addField(new Field("sport", SportFieldNum, 0, 1, 0, "", false, Profile.Type.SPORT));
        
        courseMesg.addField(new Field("name", NameFieldNum, 7, 1, 0, "", false, Profile.Type.STRING));
        
        courseMesg.addField(new Field("capabilities", CapabilitiesFieldNum, 140, 1, 0, "", false, Profile.Type.COURSE_CAPABILITIES));
        
        courseMesg.addField(new Field("sub_sport", SubSportFieldNum, 0, 1, 0, "", false, Profile.Type.SUB_SPORT));
        
    }

    public CourseMesg() {
        super(Factory.createMesg(MesgNum.COURSE));
    }

    public CourseMesg(final Mesg mesg) {
        super(mesg);
    }


    /**
     * Get sport field
     *
     * @return sport
     */
    public Sport getSport() {
        Short value = getFieldShortValue(4, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Sport.getByValue(value);
    }

    /**
     * Set sport field
     *
     * @param sport
     */
    public void setSport(Sport sport) {
        setFieldValue(4, 0, sport.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get name field
     *
     * @return name
     */
    public String getName() {
        return getFieldStringValue(5, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set name field
     *
     * @param name
     */
    public void setName(String name) {
        setFieldValue(5, 0, name, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get capabilities field
     *
     * @return capabilities
     */
    public Long getCapabilities() {
        return getFieldLongValue(6, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set capabilities field
     *
     * @param capabilities
     */
    public void setCapabilities(Long capabilities) {
        setFieldValue(6, 0, capabilities, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get sub_sport field
     *
     * @return sub_sport
     */
    public SubSport getSubSport() {
        Short value = getFieldShortValue(7, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return SubSport.getByValue(value);
    }

    /**
     * Set sub_sport field
     *
     * @param subSport
     */
    public void setSubSport(SubSport subSport) {
        setFieldValue(7, 0, subSport.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

}
