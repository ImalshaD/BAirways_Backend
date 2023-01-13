package com.Airways.BAirways.Database.DataBaseFunctions;

import java.util.List;
import java.util.Map;

public class PastFlight extends DataBaseFunction{
    private static String funcName = "past_flight";
    private static String functionCall = "CALL %s('%s','%s');";
    private static Type funcType = Type.PROCEDURE;
    private static String createQuery= """
            CREATE PROCEDURE %s(
            IN from_port varchar(100),
            IN to_port varchar(100)
            )
            BEGIN
            	select b.trip_id,ts.status_name,t.plane_id,t.scheduled_date,t.departure,count(b.seat_id) as c from (( booking as b join trip as t on b.trip_id=t.trip_id)join tripstatus as ts on (t.status_id=ts.tripstatus_id)
                )where (t.route_id in (
                select route_id from route where from_airport=from_port and to_airport=to_port) and b.passenger_id is not null) group by t.trip_id order by t.scheduled_date,t.departure;
            END;
            """;
    private String from_iata;
    private String to_iata;
    public PastFlight() {
        super(funcName, funcType);
    }
    public void setParams(String from_iata,String to_iata){
        this.from_iata = from_iata;
        this.to_iata  = to_iata;
    }
    @Override
    public String getCreateQuery() {
        return String.format(createQuery,funcName);
    }

    @Override
    protected String getQuery() {
        return String.format(functionCall,funcName,from_iata,to_iata);
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
