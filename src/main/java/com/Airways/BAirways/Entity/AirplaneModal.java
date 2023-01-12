package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyForiegnKey;
import com.Airways.BAirways.Utility.Annotations.MyPrimaryKey;
import com.Airways.BAirways.Utility.Annotations.MyTable;

@MyTable
public class AirplaneModal {

    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int modal_id;

    @MyColoumn
    private String modal_name;

    @MyColoumn @MyForiegnKey(table = AirplaneManufacture.class,coloumn = AirplaneManufacture.MANUFACTUREID)
    private int manufacture_id;

    @MyColoumn
    private int seating_capacity;

    @MyColoumn
    private int seat_cols_firstclass;

    @MyColoumn
    private int seat_rows_firstclass;

    @MyColoumn
    private int seat_rows_economyclass;
    @MyColoumn
    private int seat_cols_economyclass;
    @MyColoumn
    private int seat_cols_businessclass;
    @MyColoumn
    private int seat_rows_businessclass;


    protected static final String MODALNO="modal_id";
    protected static final String MODALNAME="modal_name";
    protected static final String SEATINGCAPACITY="seating_capacity";
    protected static final String SEATCOLSFIRSTCLASS = "seat_cols_firstclass";
    protected static final String SEATROWSFIRSTCLASS="seat_rows_firstclass";
    protected static final String SEATCOLSBUSINESSCLASS = "seat_cols_businessclass";
    protected static final String SEATROWSBUSINESSCLASS="seat_rows_businessclass";
    protected static final String SEATCOLSECONOMYCLASS = "seat_cols_economyclass";
    protected static final String SEATROWSECONOMYCLASS="seat_rows_economyclass";

    protected static String tableName = "AirplaneModal";

    public static String modalid(){
        return MODALNO;
    }

    public static String modalname(){
        return MODALNAME;
    }
    public static String seatingcapacity(){
        return SEATINGCAPACITY;
    }

    public static String getseatcolsfirstclass(){
        return SEATCOLSFIRSTCLASS;
    }
    public static String getseatrowsfirstclass(){
        return SEATROWSFIRSTCLASS;
    }
    public static String getSeatcolsbusinessclass(){
        return SEATCOLSBUSINESSCLASS;
    }
    public static String getSeatrowsbusinessclass(){
        return SEATROWSBUSINESSCLASS;
    }
    public static String getSeatcolseconomyclass(){
        return SEATCOLSECONOMYCLASS;
    }
    public static String getSeatrowseconomyclass(){
        return SEATROWSECONOMYCLASS;
    }

    public static String getTableName(){
        return tableName;
    }
}
