package com.Airways.BAirways.Database.DataBaseFunctions;

public class NewBooking extends DataBaseFunction{
    private int seatID;
    private int tripID;
    private int passengerID;
    private int userId;
    private String refNum;
    private String refKeu;

    protected static final String functionCall="CALL %s('%s', '%s', '%s', '%s', '%s', '%s');";

    protected static final String funcName="new_booking";
    protected static final Type funcType=Type.PROCEDURE;

    private static final String createQuery= """ 
            CREATE %s %s(
                   IN seatId INT,
                   IN tripId INT,
                   IN passengerId INT,
                   IN userId INT,
                   IN referenceNumber VARCHAR(100),
                   IN refkey VARCHAR(100)
                 )
                 BEGIN
                   declare bookingID int default 0;
                   declare passengerIdCheck int default null;
                   START TRANSACTION ;
                 		select booking_id into bookingID from booking WHERE  seat_id= seatId AND  trip_id= tripId;
                 	  SELECT passenger_id INTO passengerIdCheck
                 	  FROM booking
                 	  WHERE seat_id = seatId AND trip_id= tripId;
                 	  IF passengerIdCheck IS NULL THEN
                 		UPDATE booking
                 		SET booking_id = passengerId
                 		WHERE seat_id = seatId AND trip_id = tripId;
                 		INSERT INTO booking_log (booking_id, user_id)
                 		VALUES (bookingID, userId);
                 		INSERT INTO reference_log (booking_id, reference_num, referenceKey)
                 		VALUES (booking_id, referenceNumber, refkey);
                         select 1 as response;
                 		COMMIT;
                 	  ELSE
                 		select 0 as response;
                 		ROLLBACK;
                   END IF;
                
                 END;
            """;

    public NewBooking() {
        super(funcName, funcType);
    }
    public void setparams(int seatID,int tripID,int passengerID,int userId,String refNum,String refKeu){
        this.passengerID=passengerID;
        this.refNum=refNum;
        this.refKeu=refKeu;
        this.seatID=seatID;
        this.userId=userId;
        this.tripID=tripID;
    }
    @Override
    public String getCreateQuery() {
        return String.format(createQuery,funcType,funcName);
    }

    @Override
    protected String getQuery() {
        return String.format(functionCall,funcName,seatID,tripID,passengerID,userId,refKeu,refKeu);
    }
}
