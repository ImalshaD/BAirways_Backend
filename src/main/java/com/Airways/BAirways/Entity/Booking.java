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
public class Booking {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int booking_id;
    @MyColoumn @MyForiegnKey(table = Trip.class,coloumn = Trip.TRIPID)
    private int trip_id;
    @MyColoumn @MyForiegnKey(table= Passenger.class,coloumn = Passenger.PASSENGERID)
    private int passenger_id;
    @MyColoumn @MyForiegnKey(table= Seat.class,coloumn = Seat.SEATID)
    private int seat_id;
    @MyColoumn @MyForiegnKey(table= PassengerClass.class, coloumn = PassengerClass.CLASSID)
    private int class_id;
    @MyColoumn
    private double cost;


    protected static final String BOOKINGID="booking_id";
    protected static final String TRIPID="trip_id";
    protected static final String PASSENGERID="passenger_id";
    protected static final String SEATID="seat_id";
    protected static final String CLASSID="class_id";
    protected static final String COST="cost";

    protected static final String tableName ="Booking";

    public static String bookingid(){
        return BOOKINGID;
    }


    public static String tripid(){
        return TRIPID;
    }


    public static String passengerid(){
        return PASSENGERID;
    }


    public static String seatid(){
        return SEATID;
    }


    public static String classid(){
        return CLASSID;
    }


    public static String cost(){
        return COST;
    }

    public static String tablename(){
        return tableName;
    }

}
