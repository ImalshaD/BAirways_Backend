package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.*;
import com.Airways.BAirways.Utility.QueryHelper.Query.Trigger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MyTable
public class Booking_log {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int log_num;
    @MyColoumn @MyForiegnKey(table = RegisteredUser.class, coloumn = RegisteredUser.ID)
    private int user_id;
    @MyColoumn @MyForiegnKey(table= Booking.class, coloumn = Booking.BOOKINGID)
    private int booking_id;
    @MyColoumn @MyForiegnKey(table= Status.class,coloumn = Status.STATUSID)
    private int status_id;
    @MyTrigger
    public static Trigger discount_trigger(){
        String triggerName="discount_trigger";
        String query= """
                CREATE TRIGGER %s
                AFTER INSERT ON booking_log
                FOR EACH ROW
                BEGIN
                	DECLARE done INT DEFAULT 0;
                	DECLARE typeid INT DEFAULT 0;
                    DECLARE discount_per DECIMAL(8,2) default 0;
                    DECLARE cost_per DECIMAL(8,2) default 0;
                    select type_id into typeid from registereduser where user_id= new.user_id;
                    select discount into discount_per from type where type_id = typeid;
                    select cost into cost_per from booking where booking_id=new.booking_id;
                    set @newcost = cost_per *(100-discount_per)/(100);
                    update booking
                    set cost=@newcost
                    where booking_id=new.booking_id;
                end;
                """;
        return new Trigger(triggerName,String.format(query,triggerName));
    }
    protected static final String LOGNUM="log_num";
    protected static final String USERID="user_id";
    protected static final String BOOKINGID="booking_id";
    protected static final String tableName="Booking_log";
    protected static final String STATUSID="status_id";
    public static String statusid(){
        return STATUSID;
    }

    public static String lognum(){
        return LOGNUM;
    }


    public static String userid(){
        return USERID;
    }


    public static String bookingid(){
        return BOOKINGID;
    }


    public static String tablename(){
        return tableName;
    }

}
