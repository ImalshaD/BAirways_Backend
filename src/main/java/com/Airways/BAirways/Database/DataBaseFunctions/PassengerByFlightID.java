package com.Airways.BAirways.Database.DataBaseFunctions;

import java.util.List;
import java.util.Map;

public class PassengerByFlightID extends DataBaseFunction{
    private static String funcName="passengers_by_flight_id";
    private static Type funcType = Type.PROCEDURE;
    private static String functionCall = "CALL %s('%s',%s);";

    private static String createQuery = """
            CREATE PROCEDURE %s(
            IN flight_id int,
            IN over18 boolean
            )
            BEGIN
            	DECLARE trip_id_use int default 0;
                select t.trip_id into trip_id_use from (trip as t join tripstatus as ts on t.status_id=ts.tripstatus_id)
                where (t.plane_id=flight_id and ts.status_name="SCHEDULED" ) order by scheduled_date,departure limit 1;
            	if (over18) then
            		select * from (passenger as p join booking as b on p.passenger_id =b.passenger_id) where b.trip_id=trip_id_use
                    and p.age>18;
            	else
            		select * from (passenger as p join booking as b on p.passenger_id =b.passenger_id) where b.trip_id=trip_id_use
                    and p.age<18;
            	end if;
            END;
            """;
    private int flight_id;
    private boolean over18;
    public PassengerByFlightID() {
        super(funcName, funcType);
    }
    public void setParams(int flight_id,boolean over18){
        this.flight_id =flight_id;
        this.over18 =over18;
    }

    @Override
    public String getCreateQuery() {
        return String.format(createQuery,funcName);
    }

    @Override
    protected String getQuery() {
        return String.format(functionCall,funcName,flight_id,over18);
    }

    public List<Map<String,Object>> callfunction(){
        if (checkExistance("existsRes",funcName,funcType)==0){
            if (execute()==0) {
                return null;
            }
        }
        return jdbcTemplate.queryForList(getQuery());
    }
}
