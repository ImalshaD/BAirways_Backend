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
public class Reference_log {
    @MyColoumn @MyForiegnKey(table = Booking.class,coloumn = Booking.BOOKINGID)
    private int booking_id;
    @MyColoumn
    private String referenceKey;
    @MyColoumn @MyPrimaryKey
    private String reference_num;
    protected static final String BOOKINGID="booking_id";
    protected static final String REFERENCEKEY="referenceKey";
    protected static final String REFERENCENUM="reference_num";
    protected static final String tableName="Reference_log";

    public static String bookingid(){
        return BOOKINGID;
    }


    public static String referencekey(){
        return REFERENCEKEY;
    }


    public static String referencenum(){
        return REFERENCENUM;
    }


    public static String tablename(){
        return tableName;
    }

}
