#region Copyright
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

#endregion

using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using System.IO;
using System.Linq;

namespace Dynastream.Fit
{
    /// <summary>
    /// Implements the TrainingFile profile message.
    /// </summary>
    public class TrainingFileMesg : Mesg
    {
        #region Fields
        static class ProductSubfield
        {
            public static ushort FaveroProduct = 0;
            public static ushort GarminProduct = 1;
            public static ushort Subfields = 2;
            public static ushort Active = Fit.SubfieldIndexActiveSubfield;
            public static ushort MainField = Fit.SubfieldIndexMainField;
        }
        #endregion

        /// <summary>
        /// Field Numbers for <see cref="TrainingFileMesg"/>
        /// </summary>
        public sealed class FieldDefNum
        {
            public const byte Timestamp = 253;
            public const byte Type = 0;
            public const byte Manufacturer = 1;
            public const byte Product = 2;
            public const byte SerialNumber = 3;
            public const byte TimeCreated = 4;
            public const byte Invalid = Fit.FieldNumInvalid;
        }

        #region Constructors
        public TrainingFileMesg() : base(Profile.GetMesg(MesgNum.TrainingFile))
        {
        }

        public TrainingFileMesg(Mesg mesg) : base(mesg)
        {
        }
        #endregion // Constructors

        #region Methods
        ///<summary>
        /// Retrieves the Timestamp field</summary>
        /// <returns>Returns DateTime representing the Timestamp field</returns>
        public DateTime GetTimestamp()
        {
            Object val = GetFieldValue(253, 0, Fit.SubfieldIndexMainField);
            if(val == null)
            {
                return null;
            }

            return TimestampToDateTime(Convert.ToUInt32(val));
            
        }

        /// <summary>
        /// Set Timestamp field</summary>
        /// <param name="timestamp_">Nullable field value to be set</param>
        public void SetTimestamp(DateTime timestamp_)
        {
            SetFieldValue(253, 0, timestamp_.GetTimeStamp(), Fit.SubfieldIndexMainField);
        }
        
        ///<summary>
        /// Retrieves the Type field</summary>
        /// <returns>Returns nullable File enum representing the Type field</returns>
        new public File? GetType()
        {
            object obj = GetFieldValue(0, 0, Fit.SubfieldIndexMainField);
            File? value = obj == null ? (File?)null : (File)obj;
            return value;
        }

        /// <summary>
        /// Set Type field</summary>
        /// <param name="type_">Nullable field value to be set</param>
        public void SetType(File? type_)
        {
            SetFieldValue(0, 0, type_, Fit.SubfieldIndexMainField);
        }
        
        ///<summary>
        /// Retrieves the Manufacturer field</summary>
        /// <returns>Returns nullable ushort representing the Manufacturer field</returns>
        public ushort? GetManufacturer()
        {
            Object val = GetFieldValue(1, 0, Fit.SubfieldIndexMainField);
            if(val == null)
            {
                return null;
            }

            return (Convert.ToUInt16(val));
            
        }

        /// <summary>
        /// Set Manufacturer field</summary>
        /// <param name="manufacturer_">Nullable field value to be set</param>
        public void SetManufacturer(ushort? manufacturer_)
        {
            SetFieldValue(1, 0, manufacturer_, Fit.SubfieldIndexMainField);
        }
        
        ///<summary>
        /// Retrieves the Product field</summary>
        /// <returns>Returns nullable ushort representing the Product field</returns>
        public ushort? GetProduct()
        {
            Object val = GetFieldValue(2, 0, Fit.SubfieldIndexMainField);
            if(val == null)
            {
                return null;
            }

            return (Convert.ToUInt16(val));
            
        }

        /// <summary>
        /// Set Product field</summary>
        /// <param name="product_">Nullable field value to be set</param>
        public void SetProduct(ushort? product_)
        {
            SetFieldValue(2, 0, product_, Fit.SubfieldIndexMainField);
        }
        

        /// <summary>
        /// Retrieves the FaveroProduct subfield</summary>
        /// <returns>Nullable ushort representing the FaveroProduct subfield</returns>
        public ushort? GetFaveroProduct()
        {
            Object val = GetFieldValue(2, 0, ProductSubfield.FaveroProduct);
            if(val == null)
            {
                return null;
            }

            return (Convert.ToUInt16(val));
            
        }

        /// <summary>
        ///
        /// Set FaveroProduct subfield</summary>
        /// <param name="faveroProduct">Subfield value to be set</param>
        public void SetFaveroProduct(ushort? faveroProduct)
        {
            SetFieldValue(2, 0, faveroProduct, ProductSubfield.FaveroProduct);
        }

        /// <summary>
        /// Retrieves the GarminProduct subfield</summary>
        /// <returns>Nullable ushort representing the GarminProduct subfield</returns>
        public ushort? GetGarminProduct()
        {
            Object val = GetFieldValue(2, 0, ProductSubfield.GarminProduct);
            if(val == null)
            {
                return null;
            }

            return (Convert.ToUInt16(val));
            
        }

        /// <summary>
        ///
        /// Set GarminProduct subfield</summary>
        /// <param name="garminProduct">Subfield value to be set</param>
        public void SetGarminProduct(ushort? garminProduct)
        {
            SetFieldValue(2, 0, garminProduct, ProductSubfield.GarminProduct);
        }
        ///<summary>
        /// Retrieves the SerialNumber field</summary>
        /// <returns>Returns nullable uint representing the SerialNumber field</returns>
        public uint? GetSerialNumber()
        {
            Object val = GetFieldValue(3, 0, Fit.SubfieldIndexMainField);
            if(val == null)
            {
                return null;
            }

            return (Convert.ToUInt32(val));
            
        }

        /// <summary>
        /// Set SerialNumber field</summary>
        /// <param name="serialNumber_">Nullable field value to be set</param>
        public void SetSerialNumber(uint? serialNumber_)
        {
            SetFieldValue(3, 0, serialNumber_, Fit.SubfieldIndexMainField);
        }
        
        ///<summary>
        /// Retrieves the TimeCreated field</summary>
        /// <returns>Returns DateTime representing the TimeCreated field</returns>
        public DateTime GetTimeCreated()
        {
            Object val = GetFieldValue(4, 0, Fit.SubfieldIndexMainField);
            if(val == null)
            {
                return null;
            }

            return TimestampToDateTime(Convert.ToUInt32(val));
            
        }

        /// <summary>
        /// Set TimeCreated field</summary>
        /// <param name="timeCreated_">Nullable field value to be set</param>
        public void SetTimeCreated(DateTime timeCreated_)
        {
            SetFieldValue(4, 0, timeCreated_.GetTimeStamp(), Fit.SubfieldIndexMainField);
        }
        
        #endregion // Methods
    } // Class
} // namespace
