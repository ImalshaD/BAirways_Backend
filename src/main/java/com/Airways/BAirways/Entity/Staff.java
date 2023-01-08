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
public class Staff {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int staff_id;
    @MyColoumn
    private String first_name;
    @MyColoumn
    private String last_name;
    @MyColoumn
    private String gender;

    protected static final String STAFFID="staff_id";
    protected static final String FIRSTNAME="first_name";
    protected static final String LASTNAME="last_name";
    protected static final String GENDER="gender";
    protected static final String tableName="Staff";

    public static String staffid(){
        return STAFFID;
    }


    public static String firstname(){
        return FIRSTNAME;
    }


    public static String lastname(){
        return LASTNAME;
    }


    public static String gender(){
        return GENDER;
    }


    public static String tablename(){
        return tableName;
    }


}
