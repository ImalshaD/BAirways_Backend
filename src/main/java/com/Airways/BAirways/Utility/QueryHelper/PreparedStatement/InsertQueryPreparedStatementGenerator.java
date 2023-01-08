package com.Airways.BAirways.Utility.QueryHelper.PreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class InsertQueryPreparedStatementGenerator {
    private String tableName;
    private ArrayList<String> tableNameArray;
    private ArrayList<Object> valuesArray;
    private static  final String comma=", ";
    private static final String insertQueryTemplate="INSERT INTO %s(%s) VALUES(%s);";

    public InsertQueryPreparedStatementGenerator() {
        tableNameArray =new ArrayList<String>();
        valuesArray= new ArrayList<Object>();
    }
    public void reset() {
        tableNameArray =new ArrayList<String>();
        valuesArray= new ArrayList<Object>();
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void addValue(String coloumnName,Object data){
        tableNameArray.add(coloumnName);
        valuesArray.add(data);
    }
    public String getQuery(){
        String x= tableNameArray.get(0),y="?";
        for (String s: tableNameArray.subList(1, tableNameArray.size())){
            x+=comma;
            y+=comma;
            x+=s;
            y+="?";
        };
        return String.format(insertQueryTemplate,tableName,x,y);

    }
    public Object[] getArguments(){
        return valuesArray.toArray();
    }
    public int execute(JdbcTemplate jdbcTemplate){
        return jdbcTemplate.update(getQuery(), getArguments());
    }
}
