package com.Airways.BAirways.Utility.QueryHelper.Query;

public class DeleteQueryGenerator extends SelectQueryGenerator {
    private static String template="DELETE FROM %s";

    public DeleteQueryGenerator() {
        super();
    }

    @Override
    public void setTableName(String tableName) {
        super.setTableName(tableName);
    }
    @Override
    public String subQuery(){
        String returnString=String.format(template,tableName);
        if (combines.size() == 0) {
            return returnString;
        } else {
            whereClause = String.join(space,combines);
        }
        if(whereClause!=null){returnString+=space+String.format(where,whereClause);};
        return returnString;
    }
}
