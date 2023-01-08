package com.Airways.BAirways.Utility.QueryHelper.PreparedStatement;

public class DeleteQueryPreparedStatementGenerator extends SelectQueryPreparedStatementGenerator{
    private static String template="DELETE FROM %s";

    public DeleteQueryPreparedStatementGenerator() {
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
