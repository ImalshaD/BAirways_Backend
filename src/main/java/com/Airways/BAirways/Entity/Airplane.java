package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyForiegnKey;
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

    @MyColoumn @MyForiegnKey(table = AirplaneModal.class,coloumn = AirplaneModal.MODALNO)
    private int modal_id;
    @MyColoumn
    private int manufactured_year;

    @MyColoumn
    private String manufactured_country;


    protected static final String PLANEID="plane_id";
    protected static final String MODALID="modal_id";

    protected static final String MANUFACTUREDYEAR="manufactured_year";
    protected static final String MANUFACTUREDCOUNTRY="manufactured_country";

    protected static final String tableName="Airplane";
    public static String planeid(){
        return PLANEID;
    }
    public static String modalid(){
        return MODALID;
    }
    public static String manufacturedyear(){
        return MANUFACTUREDYEAR;
    }
    public static String manufacturedcountry(){
        return MANUFACTUREDCOUNTRY;
    }

    public static String getTableName(){
        return tableName;
    }

}
