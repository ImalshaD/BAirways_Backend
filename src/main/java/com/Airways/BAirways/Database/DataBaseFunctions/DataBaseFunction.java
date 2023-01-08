package com.Airways.BAirways.Database.DataBaseFunctions;

import com.Airways.BAirways.Database.Database;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Utility.QueryHelper.IntegerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public abstract class DataBaseFunction {
    protected enum Type{
        PROCEDURE,FUNCTION;
    }
    protected String funcName;
    protected Type funcType;
    protected static final String functionCall="SELECT %s(%s) as funcRes";
    protected static final String result="funcRes";

    protected static final Template template = new Template();
    private static final String checkQuery= """
            SELECT COUNT(1) AS %s
            FROM information_schema.routines
            WHERE routine_name = '%s' AND routine_type = '%s' AND routine_schema='%s';
            """;
    protected static final String comma=",";
    protected static final JdbcTemplate jdbcTemplate= template.getJdbcTemplate();
    public abstract String getCreateQuery();
    protected abstract String getQuery();
    public int checkExistance(String resName,String checkName,Type type){
        String query = String.format(checkQuery,resName,checkName,type.toString(), Database.getdBname());
        System.out.println(query);
        List<Integer> x= jdbcTemplate.query(query, new IntegerRowMapper(resName));
        return x.get(0);
    }
    public int execute(){
        try {
            jdbcTemplate.execute("SET GLOBAL log_bin_trust_function_creators = 1;");
            jdbcTemplate.execute(getCreateQuery());
            jdbcTemplate.execute("SET GLOBAL log_bin_trust_function_creators = 0;");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int call() {
        if (checkExistance("existsRes",funcName,funcType)==0){
            if (execute()==0) {
                return 0;
            }
        }
        int x= jdbcTemplate.queryForObject(getQuery(), new IntegerRowMapper("response"));
        System.out.println(getQuery()+": "+x);
        return x;
    }
    public int onCreate(){
        if (checkExistance("existsRes",funcName,funcType)==0){
            if(execute()==0){
                return 0;
            }
        }
        return 1;
    }

    public DataBaseFunction(String funcName1,Type funcType1) {
        funcName=funcName1;
        funcType=funcType1;
    }
}
