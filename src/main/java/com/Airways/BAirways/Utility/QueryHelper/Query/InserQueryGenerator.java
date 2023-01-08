package com.Airways.BAirways.Utility.QueryHelper.Query;

import java.util.ArrayList;


public class InserQueryGenerator {
    private String tableName;
    private ArrayList<ArrayList<String>> values;
    private static  final String comma=", ";
    private static final String insertQueryTemplate="INSERT INTO %s(%s) VALUES(%s);";

    public InserQueryGenerator() {
        values=new ArrayList<ArrayList<String>>();
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void addValue(String coloumnName,String data){
        ArrayList<String> valueArrayList = new ArrayList<String>();
        valueArrayList.add(coloumnName);
        valueArrayList.add(data);
        values.add(valueArrayList);
    }
    public String getQuery(){
        String x=values.get(0).get(0),y=values.get(0).get(0);
        for (ArrayList<String> arrylist:values.subList(1,values.size())){
            x+=comma;
            y+=comma;
            x+=arrylist.get(0);
            y+=arrylist.get(1);
        };
        return String.format(insertQueryTemplate,tableName,x,y);

    }
}
