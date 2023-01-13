package com.Airways.BAirways.Database.DataBaseFunctions;

public class CancelBooking extends DataBaseFunction{
    private static String funcName="cancel_booking";

    private static String functionCall ="CALL %s('%s');";
    private static Type funcType = Type.PROCEDURE;

    private int booking_id;

    private static String createQuery= """
            CREATE PROCEDURE %s(
            IN booking_id_in int
            )
            BEGIN
            	DECLARE cost_use DECIMAL(8,2);
                DECLARE route_id_use int;
                DECLARE trip_id_use int;
                DECLARE class_id_use int;
            	START TRANSACTION;
            		update booking_log set status_id='3'  where booking_id=booking_id_in;
                    select trip_id,class_id into trip_id_use,class_id_use from booking where booking_id=booking_id_in;
                    select route_id into route_id_use from trip where trip_id=trip_id_use;
                    select price into cost_use from prices where route_id=route_id_use
                    and class_id=class_id_use;
                    update booking set passenger_id=null,cost=cost_use where booking_id=booking_id_in;
                    delete from reference_log where booking_id=booking_id_in;
                    select 1 as response;
            	commit;
                  
            END;
            """;
    public CancelBooking() {
        super(funcName,funcType);
    }
    public void setParams(int booking_id){
        this.booking_id=booking_id;
    }
    @Override
    public String getCreateQuery() {
        return String.format(createQuery,funcName);
    }

    @Override
    protected String getQuery() {
        return String.format(functionCall,funcName,booking_id);
    }
}
