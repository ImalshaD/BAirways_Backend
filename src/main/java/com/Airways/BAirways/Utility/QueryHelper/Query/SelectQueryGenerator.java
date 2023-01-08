package com.Airways.BAirways.Utility.QueryHelper.Query;

import com.Airways.BAirways.Utility.Exeptions.OperatorExeption;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.ArrayList;

public class SelectQueryGenerator {
    protected String tableName;
    protected static final String space=" ";
    protected static final String comma=",";
    protected static final String template="SELECT %s FROM %s";
    protected static final String where="WHERE(%s)";
    protected static final String groupBy=" GROUP BY (%s)";
    protected static final String orderBy=" ORDER BY (%s)";
    protected String whereClause;
    private String groupbyClause;
    private String orderbyClause;
    private String fieldsClause;
    private ArrayList<String> fields;
    private boolean initCondition;
    protected ArrayList<String> combines;
    private ArrayList<String> groupby;
    private ArrayList<String> orderby;
    public SelectQueryGenerator(){
        combines=new ArrayList<String>();
        groupby=new ArrayList<String>();
        fields=new ArrayList<String>();
        orderby=new ArrayList<String>();
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
    public void subQueryAsFirstCondition(String field,Operators.SubQueryOperators subQueryOperators, SelectQueryGenerator selectQueryGenerator){
        try {
            initCondition=true;
            combines.add(Operators.getOperator(subQueryOperators, field, selectQueryGenerator.subQuery()));
        }catch (OperatorExeption ex){
            initCondition=false;
        }
    }
    public void firstCondition(String field,Operators operator,Object value){
        try {
            initCondition=true;
            combines.add(Operators.getOperator(operator, field, value));
        }catch (OperatorExeption ex){
            initCondition=false;
        }
    }
    public void joinCondition(JoinOperators joinOperator,String field,Operators operator,Object value){
        try {
            String mainClause = Operators.getOperator(operator, field, value);
            combines.add(JoinOperators.getOperator(joinOperator,mainClause));
        }catch (OperatorExeption e){

        }
    }
    public void joinSubQuery(JoinOperators joinOperator,String field,Operators.SubQueryOperators subQueryOperator,SelectQueryGenerator selectQueryGenerator) {
        try {
            String mainClause = Operators.getOperator(subQueryOperator, field,selectQueryGenerator.subQuery());
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
}
