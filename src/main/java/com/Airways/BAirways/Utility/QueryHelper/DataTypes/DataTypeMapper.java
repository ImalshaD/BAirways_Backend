package com.Airways.BAirways.Utility.QueryHelper.DataTypes;

import com.Airways.BAirways.Utility.Exeptions.DataTypeExeption;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class DataTypeMapper{
    public static DataType map(int integer) {
        return new INT();
    }
    public static DataType map(String s) {
        return new VARCHAR(100);
    }
    public static DataType map(float f) {
        return new DECIMAL(2,8);
    }
    public static DataType map(double d) {
        return new DECIMAL(2,8);
    }

    public static DataType map(Class c) throws DataTypeExeption {
        if (c.equals(int.class)) {
            return new INT();
        }else if (c.equals(long.class)){
            return new INT();
        }else if (c.equals(String.class)){
            return new VARCHAR(100);
        }else if(c.equals(float.class)){
            return new DECIMAL(2,8);
        }else if (c.equals(double.class)) {
            return new DECIMAL(2, 8);
        }else if(c.equals(LocalDate.class)) {
            return new Date();
        }else if (c.equals(LocalTime.class)) {
            return new Time();
        }else if(c.equals(File.class)){
            return new BLOB();
        }else{
            throw new DataTypeExeption("Invalid data type conversion");
        }
    }
}

