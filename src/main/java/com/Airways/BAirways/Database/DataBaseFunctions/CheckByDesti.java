package com.Airways.BAirways.Database.DataBaseFunctions;

import java.time.LocalDate;

public class CheckByDesti extends DataBaseFunction{
    private static String funcName = "Check_by_destination";
    private static Type funcType = Type.FUNCTION;

    private static String functionCall = "SELECT %s('%s','%s','%s') as response";
    private static String dateFormat = "%s-%s-%s";

    private static String createQuery= """
            CREATE FUNCTION %s(
            from_date date,
            to_date date,
            destination varchar(100)) RETURNS int
                READS SQL DATA
            BEGIN
            	DECLARE return_val int default 0;
                select count(*) into return_val from ((booking as b join trip as t on b.trip_id=t.trip_id) join
                route as r on r.route_id=t.route_id) where (r.to_airport=destination and  t.scheduled_date between
                from_date and to_date and b.passenger_id is not null);
            RETURN return_val;
            END
            
            """;
    private LocalDate from_date;
    private LocalDate to_date;
    private String destination;
    public void setParams(LocalDate from_date,LocalDate to_date,String destination){
        this.from_date = from_date;
        this.to_date = to_date;
        this.destination = destination;
    }
    public CheckByDesti() {
        super(funcName, funcType);
    }

    @Override
    public String getCreateQuery() {
        return String.format(createQuery,funcName);
    }

    @Override
    protected String getQuery() {
        int from_year = from_date.getYear();
        int from_day = from_date.getDayOfMonth();
        int from_month = from_date.getMonthValue();

        int to_year = to_date.getYear();
        int to_day = to_date.getDayOfMonth();
        int to_month = to_date.getMonthValue();
        String from_string = String.format(dateFormat,from_year,from_month,from_day);
        String to_string = String.format(dateFormat,to_year,to_month,to_day);
        return String.format(functionCall,funcName,from_string,to_string,destination);
    }

    @Override
    public int call(){
        if (from_date.isBefore(to_date)){
            return  super.call();
        }else{
            return  0;
        }
    }
}
