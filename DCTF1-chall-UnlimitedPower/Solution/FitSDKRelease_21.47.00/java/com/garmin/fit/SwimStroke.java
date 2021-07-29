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


public enum SwimStroke {
    FREESTYLE((short)0),
    BACKSTROKE((short)1),
    BREASTSTROKE((short)2),
    BUTTERFLY((short)3),
    DRILL((short)4),
    MIXED((short)5),
    IM((short)6),
    INVALID((short)255);

    protected short value;

    private SwimStroke(short value) {
        this.value = value;
    }

    public static SwimStroke getByValue(final Short value) {
        for (final SwimStroke type : SwimStroke.values()) {
            if (value == type.value)
                return type;
        }

        return SwimStroke.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @return The string representation of the value
     */
    public static String getStringFromValue( SwimStroke value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}
