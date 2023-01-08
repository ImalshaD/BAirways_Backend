package com.Airways.BAirways.Utility.QueryHelper.PreparedStatement;

import com.Airways.BAirways.Utility.Exeptions.OperatorExeption;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class SelectQueryPreparedStatementGenerator {
    protected String tableName;
    protected static final String space=" ";
    protected static final String comma=",";
    private static final String qmark="?";
    private static final String template="SELECT %s FROM %s";
    protected static final String where="WHERE(%s)";
    private static final String groupBy=" GROUP BY (%s)";
    private static final String orderBy=" ORDER BY (%s)";
    protected String whereClause;
    private String groupbyClause;
    private String orderbyClause;
    private String fieldsClause;
    protected ArrayList<String> fields;
    private boolean initCondition;
    protected ArrayList<String> combines;
    private ArrayList<String> groupby;
    private ArrayList<String> orderby;
    protected ArrayList<Object> arguments;

    public Object[] getArguments() {
        return arguments.toArray();
    }
    public ArrayList<Object> getArgumentsList(){
        return arguments;
    }

    public SelectQueryPreparedStatementGenerator(){
        combines=new ArrayList<String>();
        groupby=new ArrayList<String>();
        fields=new ArrayList<String>();
        orderby=new ArrayList<String>();
        arguments=new ArrayList<Object>();
        fieldsClause="*";
        whereClause=null;
        groupbyClause=null;
        orderbyClause=null;
    }
    public void reset(){
        combines=new ArrayList<String>();
        groupby=new ArrayList<String>();
        fields=new ArrayList<String>();
        orderby=new ArrayList<String>();
        arguments=new ArrayList<Object>();
        fieldsClause="*";
        whereClause=null;
        groupbyClause=null;
        orderbyClause=null;
    }
    public void setFields(String ... args){
        for (String s: args){
            fields.add(s);
        }
    }
    public void setGroupby(String field){
        groupby.add(field);
    }
    public void setOrderby(String field){
        orderby.add(field);
    }
    public void setOrderbyDESC(String field){
        orderby.add(String.format("%s DESC",field));
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void subQueryAsFirstCondition(String field, Operators.SubQueryOperators subQueryOperators, SelectQueryPreparedStatementGenerator selectQueryPreparedStatementGenerator){
        try {
            initCondition=true;
            arguments.addAll(selectQueryPreparedStatementGenerator.getArgumentsList());
            combines.add(Operators.getOperator(subQueryOperators, field, selectQueryPreparedStatementGenerator.subQuery()));
        }catch (OperatorExeption ex){
            initCondition=false;
        }
    }
    public void firstCondition(String field,Operators operator,Object value){
        try {
            initCondition=true;
            arguments.add(value);
            combines.add(Operators.getPreparedOperator(operator, field, qmark));
        }catch (OperatorExeption ex){
            initCondition=false;
        }
    }
    public void joinCondition(JoinOperators joinOperator, String field, Operators operator, Object value){
        try {
            arguments.add(value);
            String mainClause = Operators.getPreparedOperator(operator, field, qmark);
            combines.add(JoinOperators.getOperator(joinOperator,mainClause));
        }catch (OperatorExeption e){

        }
    }
    public void joinSubQuery(JoinOperators joinOperator,String field,Operators.SubQueryOperators subQueryOperator,SelectQueryPreparedStatementGenerator selectQueryPreparedStatementGenerator) {
        try {
            String mainClause = Operators.getOperator(subQueryOperator, field,selectQueryPreparedStatementGenerator.subQuery());
            combines.add(JoinOperators.getOperator(joinOperator, mainClause));
        } catch (OperatorExeption e) {

        }
    }
    public String getQuery(){
        return subQuery()+";";
    }
    public String subQuery() {
        String returnString=String.format(template,fieldsClause,tableName);
        if (combines.size() == 0) {
            return returnString;
        } else {
            whereClause = String.join(space,combines);
        }
        if (groupby.size()>0){
            groupbyClause=String.join(",",groupby);
        }
        if (fields.size()>0){
            fieldsClause=String.join(",",fields);
        }
        if (orderby.size()>0){
            orderbyClause=String.join(",",orderby);
        }
        returnString= String.format(template,fieldsClause,tableName);
        if(whereClause!=null){returnString+=space+String.format(where,whereClause);};
        if (groupbyClause!=null){returnString+=String.format(groupBy,groupbyClause);}
        if(orderbyClause!=null){returnString+=String.format(orderBy,orderbyClause);}
        return returnString;
    }
    public int execute(JdbcTemplate jdbcTemplate){
        return jdbcTemplate.update(getQuery(), getArguments());
    }
}
