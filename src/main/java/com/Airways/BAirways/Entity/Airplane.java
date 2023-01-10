package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyPrimaryKey;
import com.Airways.BAirways.Utility.Annotations.MyTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MyTable
public class Airplane {

    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int plane_id;

    @MyColoumn
    private String modal;

    @MyColoumn
    private int manufactured_year;

    @MyColoumn
    private String manufactured_country;

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

    protected static final String PLANEID="plane_id";
    protected static final String MODAL="modal";
    protected static final String MANUFACTUREDYEAR="manufactured_year";
    protected static final String MANUFACTUREDCOUNTRY="manufactured_country";
    protected static final String SEATINGCAPACITY="seating_capacity";
    protected static final String SEATCOLSFIRSTCLASS = "seat_cols_firstclass";
    protected static final String SEATROWSFIRSTCLASS="seat_rows_firstclass";
    protected static final String SEATCOLSBUSINESSCLASS = "seat_cols_businessclass";
    protected static final String SEATROWSBUSINESSCLASS="seat_rows_businessclass";
    protected static final String SEATCOLSECONOMYCLASS = "seat_cols_economyclass";
    protected static final String SEATROWSECONOMYCLASS="seat_rows_economyclass";
    protected static final String tableName="Airplane";
    public static String planeid(){
        return PLANEID;
    }
    public static String modal(){
        return MODAL;
    }
    public static String manufacturedyear(){
        return MANUFACTUREDYEAR;
    }
    public static String manufacturedcountry(){
        return MANUFACTUREDCOUNTRY;
    }
    public static String seatingcapacity(){
        return SEATINGCAPACITY;
    }
    public static String getTableName(){
        return tableName;
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
}
