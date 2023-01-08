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
public class Airport {
    @MyColoumn @MyPrimaryKey
    private String IATA_Code;
    @MyColoumn @MyForiegnKey(table = Location.class,coloumn = Location.LOCATIONID)
    private int location_hierarchy_id;
    @MyColoumn
    private int time_zone;
    protected static final String IATACODE="IATA_Code";
    protected static final String LOCATIONHEIRACHIID="location_hierarchy_id";
    protected static final String TIMEZONE="time_zone";
    protected static final String tableName="Airport";
    public static String iatacode(){
        return IATACODE;
    }


    public static String locationhierarchyid(){
        return LOCATIONHEIRACHIID;
    }


    public static String timezone(){
        return TIMEZONE;
    }


    public static String tablename(){
        return tableName;
    }
}
