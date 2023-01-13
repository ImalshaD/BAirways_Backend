package com.Airways.BAirways.Database.DataBaseFunctions;

import java.util.List;
import java.util.Map;

public class Revenue extends DataBaseFunction{
    private static String funcName="revenue";
    private static Type funcType = Type.PROCEDURE;
    private static String functionCall = "CALL %s();";
    private static String createQuery = """
            CREATE PROCEDURE %s()
            BEGIN
            	select ap.modal_name,sum(b.cost) as revenue from ((booking as b join trip as t on b.trip_id = t.trip_id) right outer join (airplane as a join airplanemodal as ap on a.modal_id = ap.modal_id)\s
                on t.plane_id =a.plane_id) where(b.passenger_id is not null) group by ap.modal_id;
            END;
            """;
    public Revenue() {
        super(funcName, funcType);
    }

    @Override
    public String getCreateQuery() {
        return String.format(createQuery,funcName);
    }

    @Override
    protected String getQuery() {
        return String.format(functionCall,funcName);
    }

    public List<Map<String ,Object>> callfunction(){
        if (checkExistance("existsRes",funcName,funcType)==0){
            if (execute()==0) {
                return null;
            }
        }
        return jdbcTemplate.queryForList(getQuery());
    }
}
