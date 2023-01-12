package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyPrimaryKey;
import com.Airways.BAirways.Utility.Annotations.MyTable;
import lombok.NoArgsConstructor;

@MyTable
@NoArgsConstructor
public class AirplaneManufacture {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int manufacture_id;
    @MyColoumn(unique = true)
    private String manufacture_name;

    protected static final String tableName="AirplaneManufacture";
    protected static final String MANUFACTUREID ="manufacture_id";
    protected static final String MANUFACTURENAME="manufacture_name";

    public static String manufactureid(){
        return MANUFACTUREID;
    }

    public static String manufacturename(){
        return MANUFACTURENAME;
    }

    public static String getTableName(){
        return tableName;
    }
}
