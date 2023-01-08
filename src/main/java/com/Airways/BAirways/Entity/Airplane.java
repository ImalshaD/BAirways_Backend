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
    private int seat_cols;

    @MyColoumn
    private int seat_rows;

    protected static final String PLANEID="plane_id";
    protected static final String MODAL="modal";
    protected static final String MANUFACTUREDYEAR="manufactured_year";
    protected static final String MANUFACTUREDCOUNTRY="manufactured_country";
    protected static final String SEATINGCAPACITY="seating_capacity";
    protected static final String SEATCOLS = "seat_cols";
    protected static final String SEATROWS="seat_rows";
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

    public static String seatcols(){
        return SEATCOLS;
    }
    public static String seatrows(){
        return SEATROWS;
    }
}
