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
public class Location {
    @MyColoumn
    @MyPrimaryKey(autoIncrement = true)
    private int location_id;
    @MyColoumn
    private String location_name;
    @MyColoumn
    @MyForiegnKey(table = Location.class,coloumn = Location.LOCATIONID)
    private int parent;
    protected static final String LOCATIONID="location_id";
    protected static final String LOCATIONNAME="location_name";
    protected static final String PARENT = "parent";
    protected static final String tableName="Location";

    public static String locationid(){
        return LOCATIONID;
    }


    public static String locationname(){
        return LOCATIONNAME;
    }


    public static String parent(){
        return PARENT;
    }

    public static String tablename(){
        return tableName;
    }

}
