package com.Airways.BAirways.Utility.QueryHelper.PreparedStatement;

import java.util.ArrayList;

public class UpdateQueryPreparedStatementGenerator extends SelectQueryPreparedStatementGenerator{
    private String fieldClause;
    private ArrayList<Object> values;
    private static String template= """
            UPDATE %S
            SET %S
            """;
    private static String field="%s=?";
    public UpdateQueryPreparedStatementGenerator() {
        super();
        values= new ArrayList<Object>();
    }
    public void reset() {
        super.reset();
        values= new ArrayList<Object>();
    }
    @Override
    @Deprecated
    public void setFields(String ... args){
        for (String s: args){
            fields.add(s);
        }
    }
    public void setField(String field,Object value){
        fields.add(field);
        values.add(value);
    }
    @Override
    public String subQuery(){
        ArrayList<String> temp = new ArrayList<String>();
        for (String s: fields){
            temp.add(String.format(field,s));
        }
        fieldClause=String.join(comma,temp);
        String returnString=String.format(template,tableName,fieldClause);
        if (combines.size() == 0) {
            return returnString;
        } else {
            whereClause = String.join(space,combines);
        }
        if(whereClause!=null){returnString+=space+String.format(where,whereClause);};
        return returnString;
    }
    @Override
    public Object[] getArguments() {
        values.addAll(arguments);
        return values.toArray();
    }
    @Override
    public ArrayList<Object> getArgumentsList(){
        values.addAll(arguments);
        return values;
    }
}
