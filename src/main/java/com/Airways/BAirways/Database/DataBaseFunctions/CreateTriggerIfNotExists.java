package com.Airways.BAirways.Database.DataBaseFunctions;

import com.Airways.BAirways.Database.Database;
import com.Airways.BAirways.Utility.QueryHelper.Query.Trigger;

public class CreateTriggerIfNotExists extends DataBaseFunction{
    protected static final String functionCall="CALL %s('%s','%s');";
    protected static final String funcName="CreateTriggerIfNotExists";
    protected static final Type funcType=Type.PROCEDURE;
    private static final String parametersTemplate="'%s'";
    private static final String onCreate= """
            CREATE PROCEDURE %s(IN trigger_name1 VARCHAR(255), IN trigger_text1 TEXT)
            BEGIN
              DECLARE trigger_exists INT DEFAULT 0;
                        
              SELECT COUNT(*) INTO trigger_exists
              FROM information_schema.triggers
              WHERE trigger_name = trigger_name1 AND
              trigger_schema = '%s';
              IF trigger_exists>0 THEN
                SELECT 0 as response;
              ELSE
                SET @stmt = trigger_text1;
                PREPARE stmt FROM @stmt;
                EXECUTE stmt;
                DEALLOCATE PREPARE stmt;
                SELECT 1 as response;
              END IF;
            END ;
            """;
    private String trigger;
    private String triggerName;
    public CreateTriggerIfNotExists() {
        super(funcName, funcType);

    }
    public void setNew(Trigger t){
        trigger=t.getTrigger();
        triggerName=t.getTriggerName();
    }
    @Override
    public String getCreateQuery() {
        return String.format(onCreate,funcName, Database.getdBname());
    }

    @Override
    protected String getQuery() {
        return String.format(functionCall,funcName,triggerName,trigger);
    }
}
