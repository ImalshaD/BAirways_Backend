package com.Airways.BAirways.Database.DataBaseFunctions;

import com.Airways.BAirways.Database.Database;
import com.Airways.BAirways.Utility.QueryHelper.IntegerRowMapper;
import com.Airways.BAirways.Utility.QueryHelper.Query.IndexQuery;
import lombok.NoArgsConstructor;


public class CreateIndexIfNotExists extends DataBaseFunction{
    protected static final String functionCall="CALL %s(%s);";
    protected static final String funcName="CreateIndexIfNotExists";
    protected static final Type funcType=Type.PROCEDURE;
    private static final String parametersTemplate="'%s','%s','%s'";

    private String table;
    private String index_name;
    private String[] args;
    private static final String SQLFunctionQuery= """
            CREATE PROCEDURE %s (IN table_name1 VARCHAR(255),IN index_name1 VARCHAR(255),IN columns1 VARCHAR(255))
            BEGIN
              DECLARE index_exists BIGINT DEFAULT 0;
                        
              SELECT COUNT(*) INTO index_exists
              FROM information_schema.statistics
              WHERE index_name = index_name1
              AND table_name = table_name1
              AND TABLE_SCHEMA='%s';
                        
              IF index_exists>0 THEN
                SELECT 0 as response;
              ELSE
                SET @sql = CONCAT('CREATE INDEX ', index_name1, ' ON ', table_name1, '(', columns1, ')');
                PREPARE stmt FROM @sql;
                EXECUTE stmt;
                DEALLOCATE PREPARE stmt;
                SELECT 1 as response;
              END IF;
            END;     
            """;

    public CreateIndexIfNotExists(String table, String index_name, String...args) {
        super(funcName,funcType);
        this.table = table;
        this.index_name = index_name;
        this.args = args;
        super.funcName=funcName;
        super.funcType=funcType;
    }
    public CreateIndexIfNotExists(IndexQuery indexQuery){
        super(funcName,funcType);
        this.table=indexQuery.getTableName();
        this.index_name=indexQuery.getIndex_name();
        this.args=indexQuery.getArgs();
    }

    public CreateIndexIfNotExists() {
        super(funcName, funcType);
    }

    public void setNew(IndexQuery indexQuery){
        this.table=indexQuery.getTableName();
        this.index_name=indexQuery.getIndex_name();
        this.args=indexQuery.getArgs();
    }
    public void setNew(String table, String index_name, String...args){
        this.table = table;
        this.index_name = index_name;
        this.args = args;
    }
    @Override
    public String getCreateQuery() {
        return String.format(SQLFunctionQuery,funcName, Database.getdBname());
    }

    @Override
    protected String getQuery() {
        String columns=String.join(comma,args);
        String parameters=String.format(parametersTemplate,table,index_name,columns);
        String query = String.format(functionCall,funcName,parameters);
        return query;
    }

}
