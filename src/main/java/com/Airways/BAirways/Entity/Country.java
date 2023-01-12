package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyPrimaryKey;
import com.Airways.BAirways.Utility.Annotations.MyTable;
import lombok.NoArgsConstructor;

@MyTable
@NoArgsConstructor
public class Country {
    @MyColoumn
    @MyPrimaryKey(autoIncrement = true)
    private int country_id;
    @MyColoumn
    private int country_phone_code;
    @MyColoumn
    private String name;
    @MyColoumn
    private String nationality;

    protected static final String COUNTRYID="country_id";
    protected static final String COUNTRYPHONECODE="country_phone_code";
    protected static final String NAME="name";
    protected static final String NATIONALITY="nationality";
    private static final String tableName="Country";

    public static String countryid(){
        return COUNTRYID;
    }


    public static String countryphonecode(){
        return COUNTRYPHONECODE;
    }


    public static String name(){
        return NAME;
    }


    public static String nationality(){
        return NATIONALITY;
    }
    public static String getTableName(){
        return tableName;
    }
}
