package com.Airways.BAirways.Utility.QueryHelper.Query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ForeignKeyQuery {
    private String col;
    private String table;
    private String fkTableName;
    private String fkColName;


    public String getTable() {
        return table;
    }
    public String getCol() {
        return col;
    }

    public String getFkTableName() {
        return fkTableName;
    }

    public String getFkColName() {
        return fkColName;
    }
}
