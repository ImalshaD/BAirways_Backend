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
public class PassengerClass {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int class_id;
    @MyColoumn(unique = true)
    private String class_name;

    protected static final String CLASSID="class_id";
    protected static final String CLASSNAME="class_name";
    protected static final String tableName="PassengerClass";

    public static String classid(){
        return CLASSID;
    }


    public static String classname(){
        return CLASSNAME;
    }


    public static String tablename(){
        return tableName;
    }

}
