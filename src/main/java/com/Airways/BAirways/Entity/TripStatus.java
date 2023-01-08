package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyPrimaryKey;
import com.Airways.BAirways.Utility.Annotations.MyTable;

@MyTable
public class TripStatus {

    @MyColoumn
    @MyPrimaryKey(autoIncrement = true)
    private int tripstatus_id;
    @MyColoumn
    private String status_name;

    protected static final String TRIPSTATUSID="tripstatus_id";
    protected static final String STATUSNAME="status_name";
    protected static final String tableName="TripStatus";

    public static String tripstatusid(){
        return TRIPSTATUSID;
    }
    public static String statusname(){
        return STATUSNAME;
    }
    public static String tablename(){
        return tableName;
    }
}
