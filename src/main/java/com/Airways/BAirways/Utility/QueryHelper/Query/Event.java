package com.Airways.BAirways.Utility.QueryHelper.Query;

import com.Airways.BAirways.Database.Database;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.IntegerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Event {
    private String trigger_name;
    private String query;
    private String dateString;
    private static String dateString_template = "%s-%s-%s %s:%s:%s";
    private static Template mytemplate = new Template();
    private AbstractLogger logger = LoggerBuilder.getLogger();
    private static JdbcTemplate jdbcTemplate = mytemplate.getJdbcTemplate();

    private static String existsCheck= """
            SELECT COUNT(*) as %s FROM INFORMATION_SCHEMA.EVENTS
            WHERE EVENT_NAME = '%s' AND EVENT_SCHEMA = '%s';
            """;
    private void updateTiming(){
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int date = today.getDayOfMonth();
        int hours = now.getHour();
        int minutes = now.getMinute();
        int seconds = now.getSecond();
        if (minutes<55){
            minutes+=4;
        }else{
            hours+=1;
            minutes-=50;
        }
        dateString  = String.format(dateString_template,year,month,date,hours,minutes,seconds);
    }
    public Event(String trigger_name, String query) {
        this.trigger_name = trigger_name;
        this.query = query;
    }
    public boolean checkExistence(){
        String res_Name = "event_exists";
        String query = String.format(existsCheck,res_Name,trigger_name, Database.getdBname());
        List<Integer> x= jdbcTemplate.query(query, new IntegerRowMapper(res_Name));
        int count = x.get(0);
        if (count==0){
            logger.log(query);
            logger.log("0");
            return false;
        }else{
            logger.log(query);
            logger.log("1");
            return true;
        }
    }
    public int create(){
        if (checkExistence()){
            return 0;
        }else{
            try{
                updateTiming();
                jdbcTemplate.execute(String.format(query,trigger_name,dateString));
                return 1;
            }catch (Exception e){
                logger.log(e.toString());
                return 0;

            }
        }
    }
    public String getQuery(){
        return String.format(query,trigger_name,dateString);
    }
}
