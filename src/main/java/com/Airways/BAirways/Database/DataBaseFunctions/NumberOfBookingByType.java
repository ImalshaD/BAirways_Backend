package com.Airways.BAirways.Database.DataBaseFunctions;

import org.springframework.data.relational.core.sql.In;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfBookingByType extends DataBaseFunction{
    private static String funcName="number_of_booking_by_type";
    private static Type funcType = Type.PROCEDURE;
    
    private LocalDate from_date;
    
    private LocalDate to_date;
    
    private static String functionCall = "CALL %s ('%s','%s');";
    
    private static String dateFormat = "%s-%s-%s";

    private static String createQuery= """
            CREATE PROCEDURE %s(
            IN from_date date,
            IN to_date date)
            BEGIN
            	select ps.class_name,count(*) as result from ( (booking as b join passengerclass as ps on b.class_id = ps.class_id)
                join trip as t on b.trip_id = t.trip_id)
                where (t.scheduled_date between from_date and to_date) and b.passenger_id is not null group by b.class_id;
            END;
            """;
    public NumberOfBookingByType() {
        super(funcName,funcType);
    }
    public void setParams(LocalDate from_date,LocalDate to_date){
        this.from_date = from_date;
        this.to_date = to_date;
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

        return String.format(functionCall,funcName,from_string,to_string);
    }
    public List<Map<String,Object>> callresults(){
        if (checkExistance("existsRes",funcName,funcType)==0){
            if (execute()==0) {
                return null;
            }
        }
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(getQuery());
        return  maps;
    }
}
