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
public class Type {
    @MyColoumn
    @MyPrimaryKey(autoIncrement = true)
    private int type_id;
    @MyColoumn(unique = true)
    private String name;
    @MyColoumn
    private double discount;
    protected static  final String TYPEID = "type_id";
    protected static final String NAME="name";
    protected static final String tableName="Type";
    protected static final String DISCOUNT="discount";

    public static String typeid(){
        return TYPEID;
    }


    public static String name(){
        return NAME;
    }


    public static String tablename(){
        return tableName;
    }
    public static String discount() { return DISCOUNT; }


}
