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


#if !defined(FIT_WATCHFACE_SETTINGS_MESG_HPP)
#define FIT_WATCHFACE_SETTINGS_MESG_HPP

#include "fit_mesg.hpp"

namespace fit
{

class WatchfaceSettingsMesg : public Mesg
{
public:
    class FieldDefNum final
    {
    public:
       static const FIT_UINT8 MessageIndex = 254;
       static const FIT_UINT8 Mode = 0;
       static const FIT_UINT8 Layout = 1;
       static const FIT_UINT8 Invalid = FIT_FIELD_NUM_INVALID;
    };

    WatchfaceSettingsMesg(void) : Mesg(Profile::MESG_WATCHFACE_SETTINGS)
    {
    }

    WatchfaceSettingsMesg(const Mesg &mesg) : Mesg(mesg)
    {
    }

    ///////////////////////////////////////////////////////////////////////
    // Checks the validity of message_index field
    // Returns FIT_TRUE if field is valid
    ///////////////////////////////////////////////////////////////////////
    FIT_BOOL IsMessageIndexValid() const
    {
        const Field* field = GetField(254);
        if( FIT_NULL == field )
        {
            return FIT_FALSE;
        }

        return field->IsValueValid();
    }

    ///////////////////////////////////////////////////////////////////////
    // Returns message_index field
    ///////////////////////////////////////////////////////////////////////
    FIT_MESSAGE_INDEX GetMessageIndex(void) const
    {
        return GetFieldUINT16Value(254, 0, FIT_SUBFIELD_INDEX_MAIN_FIELD);
    }

    ///////////////////////////////////////////////////////////////////////
    // Set message_index field
    ///////////////////////////////////////////////////////////////////////
    void SetMessageIndex(FIT_MESSAGE_INDEX messageIndex)
    {
        SetFieldUINT16Value(254, messageIndex, 0, FIT_SUBFIELD_INDEX_MAIN_FIELD);
    }

    ///////////////////////////////////////////////////////////////////////
    // Checks the validity of mode field
    // Returns FIT_TRUE if field is valid
    ///////////////////////////////////////////////////////////////////////
    FIT_BOOL IsModeValid() const
    {
        const Field* field = GetField(0);
        if( FIT_NULL == field )
        {
            return FIT_FALSE;
        }

        return field->IsValueValid();
    }

    ///////////////////////////////////////////////////////////////////////
    // Returns mode field
    ///////////////////////////////////////////////////////////////////////
    FIT_WATCHFACE_MODE GetMode(void) const
    {
        return GetFieldENUMValue(0, 0, FIT_SUBFIELD_INDEX_MAIN_FIELD);
    }

    ///////////////////////////////////////////////////////////////////////
    // Set mode field
    ///////////////////////////////////////////////////////////////////////
    void SetMode(FIT_WATCHFACE_MODE mode)
    {
        SetFieldENUMValue(0, mode, 0, FIT_SUBFIELD_INDEX_MAIN_FIELD);
    }

    ///////////////////////////////////////////////////////////////////////
    // Checks the validity of layout field
    // Returns FIT_TRUE if field is valid
    ///////////////////////////////////////////////////////////////////////
    FIT_BOOL IsLayoutValid() const
    {
        const Field* field = GetField(1);
        if( FIT_NULL == field )
        {
            return FIT_FALSE;
        }

        return field->IsValueValid();
    }

    ///////////////////////////////////////////////////////////////////////
    // Returns layout field
    ///////////////////////////////////////////////////////////////////////
    FIT_BYTE GetLayout(void) const
    {
        return GetFieldBYTEValue(1, 0, FIT_SUBFIELD_INDEX_MAIN_FIELD);
    }

    ///////////////////////////////////////////////////////////////////////
    // Set layout field
    ///////////////////////////////////////////////////////////////////////
    void SetLayout(FIT_BYTE layout)
    {
        SetFieldBYTEValue(1, layout, 0, FIT_SUBFIELD_INDEX_MAIN_FIELD);
    }

    ///////////////////////////////////////////////////////////////////////
    // Checks the validity of digital_layout field
    // Returns FIT_TRUE if field is valid
    ///////////////////////////////////////////////////////////////////////
    FIT_BOOL IsDigitalLayoutValid() const
    {
        const Field* field = GetField(1);
        if( FIT_NULL == field )
        {
            return FIT_FALSE;
        }

        if( !CanSupportSubField( field, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_DIGITAL_LAYOUT ) )
        {
            return FIT_FALSE;
        }

        return field->IsValueValid(0, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_DIGITAL_LAYOUT);
    }


    ///////////////////////////////////////////////////////////////////////
    // Returns digital_layout field
    ///////////////////////////////////////////////////////////////////////
    FIT_DIGITAL_WATCHFACE_LAYOUT GetDigitalLayout(void) const
    {
        return GetFieldENUMValue(1, 0, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_DIGITAL_LAYOUT);
    }

    ///////////////////////////////////////////////////////////////////////
    // Set digital_layout field
    ///////////////////////////////////////////////////////////////////////
    void SetDigitalLayout(FIT_DIGITAL_WATCHFACE_LAYOUT digitalLayout)
    {
        SetFieldENUMValue(1, digitalLayout, 0, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_DIGITAL_LAYOUT);
    }

    ///////////////////////////////////////////////////////////////////////
    // Checks the validity of analog_layout field
    // Returns FIT_TRUE if field is valid
    ///////////////////////////////////////////////////////////////////////
    FIT_BOOL IsAnalogLayoutValid() const
    {
        const Field* field = GetField(1);
        if( FIT_NULL == field )
        {
            return FIT_FALSE;
        }

        if( !CanSupportSubField( field, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_ANALOG_LAYOUT ) )
        {
            return FIT_FALSE;
        }

        return field->IsValueValid(0, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_ANALOG_LAYOUT);
    }


    ///////////////////////////////////////////////////////////////////////
    // Returns analog_layout field
    ///////////////////////////////////////////////////////////////////////
    FIT_ANALOG_WATCHFACE_LAYOUT GetAnalogLayout(void) const
    {
        return GetFieldENUMValue(1, 0, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_ANALOG_LAYOUT);
    }

    ///////////////////////////////////////////////////////////////////////
    // Set analog_layout field
    ///////////////////////////////////////////////////////////////////////
    void SetAnalogLayout(FIT_ANALOG_WATCHFACE_LAYOUT analogLayout)
    {
        SetFieldENUMValue(1, analogLayout, 0, (FIT_UINT16) Profile::WATCHFACE_SETTINGS_MESG_LAYOUT_FIELD_ANALOG_LAYOUT);
    }

};

} // namespace fit

#endif // !defined(FIT_WATCHFACE_SETTINGS_MESG_HPP)
