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
public class Status {
    @MyColoumn
    @MyPrimaryKey(autoIncrement = true)
    private int status_id;
    @MyColoumn(unique = true)
    private String name;
    protected static final String STATUSID="status_id";
    protected static final String NAME="name";
    protected static final String tableName="Status";

    public static String statusid(){
        return STATUSID;
    }


    public static String name(){
        return NAME;
    }


    public static String tablename(){
        return tableName;
    }


}
