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
public class Role {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int role_id;
    @MyColoumn
    private String title;

    protected static final String ROLEID="role_id";
    protected static final String TITLE="title";
    protected static final String tableName="Role";

    public static String roleid(){
        return ROLEID;
    }


    public static String title(){
        return TITLE;
    }


    public static String tablename(){
        return tableName;
    }

}
