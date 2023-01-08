package com.Airways.BAirways.Repositary;


import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.DeleteQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.InsertQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.UpdateQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public abstract class Repo<T> {


    private static final Template template = new Template();
    protected InsertQueryPreparedStatementGenerator insertQuery;
    protected SelectQueryPreparedStatementGenerator selectQuery;
    protected DeleteQueryPreparedStatementGenerator deleteQuery;
    protected UpdateQueryPreparedStatementGenerator updateQuery;
    protected JdbcTemplate jdbcTemplate;

    public Repo(String tableName) {

        insertQuery = new InsertQueryPreparedStatementGenerator();
        selectQuery = new SelectQueryPreparedStatementGenerator();
        deleteQuery = new DeleteQueryPreparedStatementGenerator();
        updateQuery = new UpdateQueryPreparedStatementGenerator();
        jdbcTemplate = template.getJdbcTemplate();
        selectQuery.setTableName(tableName);
        updateQuery.setTableName(tableName);
        deleteQuery.setTableName(tableName);
        insertQuery.setTableName(tableName);

    }

    public abstract boolean existsByPrimaryKey(T dto);
    public  abstract int insertRecord(T dto);
    @Deprecated
    public  abstract int updateRecord(T dtoOld,T dtoNew);
    public abstract int deleteRecord(T dto);
    protected void prepare(){
        selectQuery.reset();
        selectQuery.setFields("Count(*) as result");
    }
    protected boolean exists(){
        List<Map<String, Object>> list = null;
        try {
            list = jdbcTemplate.queryForList(selectQuery.getQuery(), selectQuery.getArguments());
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
        Map<String,Object> map1 = list.get(0);
        long count =(long) map1.get("result");
        if (count==0){
            return false;
        }
        return true;
    }
    protected void prepareDelete(){
        deleteQuery.reset();
    }
    protected int delete(){
        try {
            return jdbcTemplate.update(deleteQuery.getQuery(), deleteQuery.getArguments());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    protected void prepareInsert(){
        insertQuery.reset();
    }
    protected int insert(){
        try {
            return jdbcTemplate.update(insertQuery.getQuery(), insertQuery.getArguments());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    protected void prepareUpdate(){
        updateQuery.reset();
    }

    protected int update(){
        try {
            return jdbcTemplate.update(updateQuery.getQuery(), updateQuery.getArguments());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    protected void prepareGet(){
        selectQuery.reset();
    }
    protected List<Map<String,Object>> get(){
        try {
            return jdbcTemplate.queryForList(selectQuery.getQuery(), selectQuery.getArguments());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
