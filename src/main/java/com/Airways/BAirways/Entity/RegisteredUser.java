package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MyTable
public class RegisteredUser {
    @MyColoumn
    @MyPrimaryKey(autoIncrement = true)
    private int user_id;
    @MyColoumn(unique = true)
    @MyIndex(indexName = "passwordIndex")
    private String user_name;
    @MyColoumn
    private String first_name;
    @MyColoumn
    private String last_name;
    @MyColoumn
    @MyIndex(indexName = "passwordIndex")
    private String password;
    @MyColoumn @MyForiegnKey(table = Type.class, coloumn = Type.TYPEID)
    private int type_id;
    @MyColoumn
    private String email;
    protected static final String ID= "user_id";
    protected static final String USERNAME="user_name";
    protected static final String FIRSTNAME="first_name";
    protected static final String LASTNAME="last_name";
    protected static final String PASSWORD="password";
    protected static final String TYPE_ID="type_id";
    protected static final String EMAIL="email";
    private static final String TABLE_NAME="RegisteredUser";
    public static String getTableName(){
        return TABLE_NAME;
    }

    public static String id(){
        return ID;
    }


    public static String username(){
        return USERNAME;
    }


    public static String firstname(){
        return FIRSTNAME;
    }


    public static String lastname(){
        return LASTNAME;
    }


    public static String password(){
        return PASSWORD;
    }


    public static String type_id(){
        return TYPE_ID;
    }


    public static String email(){
        return EMAIL;
    }

}
