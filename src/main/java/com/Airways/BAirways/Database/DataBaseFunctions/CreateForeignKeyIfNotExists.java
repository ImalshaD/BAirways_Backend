package com.Airways.BAirways.Database.DataBaseFunctions;

import com.Airways.BAirways.Database.Database;
import com.Airways.BAirways.Utility.QueryHelper.Query.ForeignKeyQuery;


public class CreateForeignKeyIfNotExists extends DataBaseFunction{
    protected static final String functionCall="CALL %s(%s);";
    private static final String funcName="CreateForeignKeyIfNotExists";
    protected static final Type funcType=Type.PROCEDURE;
    private static final String parametersTemplate="'%s','%s','%s','%s'";
    private String table;
    private String col;
    private String fkTable;
    private String fkCol;
    private static final String SQLFunctionQuery= """
            
            CREATE PROCEDURE %s (
              IN table_name1 VARCHAR(255),
              IN column_name1 VARCHAR(255),
              IN ref_table_name1 VARCHAR(255),
              IN ref_column_name1 VARCHAR(255)
            )
            BEGIN
              DECLARE fk_exists BIGINT DEFAULT 0;
            
              SELECT COUNT(*) INTO fk_exists
              FROM information_schema.key_column_usage
              WHERE table_name = table_name1
              AND column_name = column_name1
              AND referenced_table_name = ref_table_name1
              AND referenced_column_name = ref_column_name1
              AND TABLE_SCHEMA='%s';
            
              IF fk_exists>0 THEN
                SELECT 0 as response;
              ELSE
                SET @sql = CONCAT(
                  'ALTER TABLE ', table_name1, ' ADD FOREIGN KEY (', column_name1, ') REFERENCES ', ref_table_name1, '(', ref_column_name1, ')'
                );
                PREPARE stmt FROM @sql;
                EXECUTE stmt;
                DEALLOCATE PREPARE stmt;
                SELECT 1 as response;
              END IF;
            END;
            """;

    public CreateForeignKeyIfNotExists(ForeignKeyQuery foreignKeyQuery) {
        super(funcName,funcType);
        this.table = foreignKeyQuery.getTable();
        this.col = foreignKeyQuery.getCol();
        this.fkTable = foreignKeyQuery.getFkTableName();
        this.fkCol = foreignKeyQuery.getFkColName();
    }
    public CreateForeignKeyIfNotExists(){
        super(funcName,funcType);
    }
    public void setNew(ForeignKeyQuery foreignKeyQuery) {
        this.table = foreignKeyQuery.getTable();
        this.col = foreignKeyQuery.getCol();
        this.fkTable = foreignKeyQuery.getFkTableName();
        this.fkCol = foreignKeyQuery.getFkColName();
    }


    @Override
    public String getCreateQuery() {
        return String.format(SQLFunctionQuery,funcName, Database.getdBname());
    }

    @Override
    protected String getQuery() {
        String parameters=String.format(parametersTemplate,table,col,fkTable,fkCol);
        return String.format(functionCall,funcName,parameters);
    }
}
