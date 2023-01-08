package com.Airways.BAirways.Utility.QueryHelper.Query;


import com.Airways.BAirways.Database.Database;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.IntegerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Trigger {
    private String triggerName;
    private String actionQuery;
    private static Template mytemplate = new Template();
    private AbstractLogger logger = LoggerBuilder.getLogger();
    private static JdbcTemplate jdbcTemplate = mytemplate.getJdbcTemplate();
    private static String template= """
              SELECT COUNT(*) as %s
              FROM information_schema.triggers
              WHERE trigger_name = '%s' AND
              trigger_schema = '%s';
                """;

    public Trigger(String triggerName, String actionQuery) {
        this.triggerName = triggerName;
        this.actionQuery = actionQuery;
    }

    public String getTriggerName() {
        return triggerName;
    }
    public String getTrigger(){
        return actionQuery;
    }
    public int create(){
        if (checkExistence()){
            return 0;
        }else{
            try{
                jdbcTemplate.execute(actionQuery);
                return 1;
            }catch (Exception e){
                logger.log(e.toString());
                return 0;
            }
        }
    }
    public boolean checkExistence(){
        String res_Name = "trigger_exists";
        String query = String.format(template,res_Name,triggerName, Database.getdBname());
        List<Integer> x= jdbcTemplate.query(query, new IntegerRowMapper(res_Name));
        int count = x.get(0);
        if (count==0){
            return false;
        }else{
            return true;
        }
    }
}
