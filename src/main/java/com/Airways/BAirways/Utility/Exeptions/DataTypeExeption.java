package com.Airways.BAirways.Utility.Exeptions;

import com.Airways.BAirways.Utility.QueryHelper.DataTypes.VARCHAR;
import lombok.AllArgsConstructor;


public class DataTypeExeption extends Exception{
    private String msg;
    public DataTypeExeption(String msg) {
        super(msg);
        this.msg = msg;
    }
}
