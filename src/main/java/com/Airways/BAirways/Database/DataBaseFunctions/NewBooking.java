package com.Airways.BAirways.Database.DataBaseFunctions;

public class NewBooking extends DataBaseFunction{
    private int bookingID;
    private int passengerID;
    private int userId;
    private String refNum;
    private String refKeu;

    protected static final String functionCall="CALL %s('%s', '%s', '%s', '%s', '%s');";

    protected static final String funcName="new_booking";
    protected static final Type funcType=Type.PROCEDURE;

    private static final String createQuery= """ 
            CREATE PROCEDURE %s(
                IN passenger_id_get int,
                 IN booking_id_get int,
                 IN user_id_get int,
                 IN ref_num VARCHAR(100),
                 IN ref_key VARCHAR(100)
             )
             BEGIN
                DECLARE passenger_id_use INT default null;
                DECLARE status_id_pen INT DEFAULT 0;
                DECLARE status_id_app INT DEFAULT 0;
                DECLARE count_res INT DEFAULT 0;
                START TRANSACTION;
                    UPDATE booking set passenger_id = passenger_id_get where booking_id=booking_id_get
                     AND passenger_id is null;
                     select passenger_id into passenger_id_use from  booking where booking_id = booking_id_get;
                     if !(passenger_id_use=passenger_id_get) then
                        select 0 as response;
                         rollback;
                    else
                         select status_id into status_id_pen from status where name='PENDING';
                         select status_id into status_id_app from status where name='CANCELLED';
                         select count(*) into count_res from booking_log where booking_id = booking_id_get and status_id != status_id_app;
                         IF (count_res>0) then
                            select 0 as response;
                            rollback;
                         ELSE
                             INSERT INTO booking_log(user_id,booking_id,status_id) values(user_id_get,booking_id_get,status_id_pen);
                             INSERT INTO reference_log(booking_id,referenceKey,reference_num) values(booking_id_get,ref_num,ref_key);
                             select 1 as response;
                             COMMIT;
                         END IF;
                    END IF;
                
             END;
            """;

    public NewBooking() {
        super(funcName, funcType);
    }
    public void setparams(int passengerID,int bookingID,int userId,String refNum,String refKeu){
        this.passengerID=passengerID;
        this.refNum=refNum;
        this.refKeu=refKeu;
        this.bookingID=bookingID;
        this.userId=userId;

    }
    @Override
    public String getCreateQuery() {
        return String.format(createQuery,funcName);
    }

    @Override
    protected String getQuery() {
        return String.format(functionCall,funcName,passengerID,bookingID,userId,refNum,refKeu);
    }
}
