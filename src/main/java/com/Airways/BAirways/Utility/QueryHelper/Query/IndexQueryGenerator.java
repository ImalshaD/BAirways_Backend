package com.Airways.BAirways.Utility.QueryHelper.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndexQueryGenerator {
    private static String space=" ";
    private static String comma=",";
    private static  String template= """
            CREATE INDEX %s
            ON %s (%s);
            """;
    private String tableName;
    private ArrayList<String> indexQueries;
    private ArrayList<ArrayList<String>> pairSets;
    private HashMap<String, ArrayList<String>> indexes;

    public IndexQueryGenerator() {
        indexes=new HashMap<String,ArrayList<String>>();
        indexQueries= new ArrayList<String>();
        pairSets= new ArrayList<ArrayList<String>>();
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void setIndex(String name,String ... args){
        if (indexes.containsKey(name)){
            for (String s: args){
                indexes.get(name).add(s);
            }
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (String s: args){
                temp.add(s);
            }
            indexes.put(name,temp);
        }
    }
    public String getQuery(){
        for (String s : indexes.keySet()){
            indexQueries.add(String.format(template,s,tableName,String.join(comma,indexes.get(s))));
        }
        return String.join("\n",indexQueries);
    }

    public ArrayList<ArrayList<String>> getPairSets(){
        ArrayList<String> pair = new ArrayList<String>();
        for (String s : indexes.keySet()){
            pair = new ArrayList<String>();
            pair.add(tableName);
            pair.add(s);
            pair.add(String.join(comma,indexes.get(s)));
            pairSets.add(pair);
        }
        return pairSets;
    }
    public ArrayList<IndexQuery> getIndexQueryList(){
        ArrayList<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
        for (String s : indexes.keySet()){
            ArrayList<String> x = indexes.get(s);
            String xx[]=new String[x.size()];
            for (int i=0;i<x.size();i++){
                xx[i]=x.get(i);
            }
            indexQueries.add(new IndexQuery(tableName,s,xx));
        }
        return indexQueries;
    }
}
