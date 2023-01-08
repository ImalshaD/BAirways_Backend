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
public class Route {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int route_id;
    @MyColoumn @MyForiegnKey(table = Airport.class,coloumn = Airport.IATACODE)
    private String from_airport;
    @MyColoumn @MyForiegnKey(table = Airport.class,coloumn = Airport.IATACODE)
    private String to_airport;

    protected static final String ROUTEID = "route_id";
    protected static final String FROM="from_airport";
    protected static final String TO="to_airport";
    protected static final String tableName="route";

    public static String routeid(){
        return ROUTEID;
    }


    public static String from(){
        return FROM;
    }


    public static String to(){
        return TO;
    }


    public static String tablename(){
        return tableName;
    }

}
