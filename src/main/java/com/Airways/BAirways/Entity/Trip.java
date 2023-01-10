package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.*;
import com.Airways.BAirways.Utility.QueryHelper.Query.Trigger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MyTable
public class Trip {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int trip_id;
    @MyColoumn
    private LocalDate scheduled_date;
    @MyColoumn
    private LocalTime departure;
    @MyColoumn
    private LocalTime arrival;
    @MyColoumn @MyForiegnKey(table = Route.class,coloumn =Route.ROUTEID )
    private int route_id;
    @MyColoumn @MyForiegnKey(table= Airplane.class,coloumn = Airplane.PLANEID)
    private int plane_id;
    @MyColoumn @MyForiegnKey(table = TripStatus.class,coloumn = TripStatus.TRIPSTATUSID)
    private int status_id;

    protected static final String TRIPID="trip_id";
    protected static final String DEPARTURe="departure";
    protected static final String ARRIVAL="arrival";
    protected static final String ROUTEID="route_id";
    protected static final String PLANEID="plane_id";
    protected static final String STATUSID="status_id";
    protected static final String SCHEDULEDDATE="scheduled_date";
    protected static final String tableName="Trip";
    @MyTrigger
    public static Trigger booking_trigger(){
        String trigger_name = "booking_trigger";
        String query = """
                create trigger %s
                after insert on trip
                For each row
                	begin
                		DECLARE done INT DEFAULT FALSE;
                		DECLARE seatId INT DEFAULT 0;
                        DECLARE classID INT default 0;
                        DECLARE price_var DECIMAL(8,2) DEFAULT 0;
                		DECLARE seatCursor CURSOR FOR SELECT seat_id,class_id FROM seat where airplane_id=new.plane_id;
                		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
                		OPEN seatCursor;
                                
                    seat_loop: LOOP
                        FETCH NEXT from seatCursor INTO seatId,classID;
                        SELECT price into price_var from prices where route_id=new.route_id and class_id=classID;
                        IF done THEN
                            LEAVE seat_loop;
                        END IF;
                                
                        INSERT INTO booking (trip_id,passenger_id,seat_id,class_id,cost) VALUES (new.trip_id,null,seatID,classID,price_var);
                    END LOOP;
                                
                    CLOSE seatCursor;
                END;
                """;
        return new Trigger(trigger_name,String.format(query,trigger_name));
    }

    public static String tripid(){
        return TRIPID;
    }


    public static String departure(){
        return DEPARTURe;
    }


    public static String arrival(){
        return ARRIVAL;
    }


    public static String routeid(){
        return ROUTEID;
    }


    public static String planeid(){
        return PLANEID;
    }
    public static String statusid() {
        return STATUSID;
    }


    public static String tablename(){
        return tableName;
    }

    public static String scheduleddate(){
        return SCHEDULEDDATE;
    }

}
