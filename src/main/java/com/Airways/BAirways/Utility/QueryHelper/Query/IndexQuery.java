package com.Airways.BAirways.Utility.QueryHelper.Query;

public class IndexQuery{
    private String tableName;
    private String index_name;
    private String[] args;

    public String getTableName() {
        return tableName;
    }

    public String getIndex_name() {
        return index_name;
    }

    public String[] getArgs() {
        return args;
    }

    public IndexQuery(String tableName, String index_name, String...args) {
        this.tableName = tableName;
        this.index_name = index_name;
        this.args = args;
    }
}
