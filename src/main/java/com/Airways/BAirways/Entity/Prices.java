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
public class Prices {
    @MyColoumn
    @MyPrimaryKey
    @MyForiegnKey(table = Route.class,coloumn = Route.ROUTEID)
    private int route_id;
    @MyColoumn
    @MyPrimaryKey @MyForiegnKey(table = PassengerClass.class,coloumn = PassengerClass.CLASSID)
    private int class_id;
    @MyColoumn
    private double price;

    protected static final String ROUTEID="route_id";
    protected static final String CLASSID="class_id";
    protected static final String PRICE="price";
    protected static final String tableName="Prices";

    public static String routeid(){
        return ROUTEID;
    }


    public static String classid(){
        return CLASSID;
    }


    public static String price(){
        return PRICE;
    }


    public static String tablename(){
        return tableName;
    }



}
