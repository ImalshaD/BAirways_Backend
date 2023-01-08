package com.Airways.BAirways.Utility.QueryHelper.Query;

import com.Airways.BAirways.Utility.Exeptions.DataTypeExeption;
import com.Airways.BAirways.Utility.QueryHelper.DataTypes.DataType;
import com.Airways.BAirways.Utility.QueryHelper.DataTypes.DataTypeMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;


public class CreateQueryGenerator {
    private String tableName;
    private String primaryKey;
    private String primaryKeyType;
    private Boolean autoincrement;
    protected ArrayList<ArrayList<String>> otherColoumns;
    protected ArrayList<ArrayList<String>> foreignKeys;
    private static  final String dropTableTemplate="DROP TABLE IF EXISTS %S";
    protected static final String fkTemplate="FOREIGN KEY (%s) REFERENCES %s(%s)";
    private static final String queryTemplate="CREATE TABLE IF NOT EXISTS %s (%s);";
    private static final String alterTableForeignKey="ALTER TABLE %s ADD FOREIGN KEY (%s) REFERENCES %s(%s);";
    protected static  final String template = "%s %s";
    private static  final String autoincrementQuery = "%s %s PRIMARY KEY AUTO_INCREMENT";
    private static  final String nonAutoincrementQuery = "%s %s PRIMARY KEY";
    protected static  final String comma=", ";
    public CreateQueryGenerator() {
        otherColoumns = new ArrayList<ArrayList<String>>();
        foreignKeys = new ArrayList<ArrayList<String>>();
    }
    public void reset(){
        otherColoumns = new ArrayList<ArrayList<String>>();
        foreignKeys = new ArrayList<ArrayList<String>>();
        primaryKey=null;
        primaryKeyType=null;
        autoincrement=null;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void setPrimaryKey(String pk, DataType pkType, Boolean autoincrement){
        this.primaryKey=pk;
        this.primaryKeyType=pkType.toString();
        this.autoincrement=autoincrement;
    }
    public void setPrimaryKey(String pk,Class c,Boolean autoincrement) throws DataTypeExeption {
        DataType dType = DataTypeMapper.map(c);
        setPrimaryKey(pk,dType,autoincrement);
    }
    public void setOtherColoumn(String otherColoumn,Class c) throws DataTypeExeption {
        DataType dType = DataTypeMapper.map(c);
        setOtherColoumn(otherColoumn,dType);
    }
    public void setOtherColoumn(String otherColoumn,Class c,boolean unique) throws DataTypeExeption {
        DataType dType = DataTypeMapper.map(c);
        setOtherColoumn(otherColoumn,dType,unique);
    }
    public void setOtherColoumn(String otherColoumn,DataType dataType,boolean unique) throws DataTypeExeption {
        setOtherColoumn(otherColoumn,dataType);
    }
    public void setOtherColoumn(String otherColoumn, DataType otherColoumnType){
        ArrayList<String> newArrayList= new ArrayList<String>();
        newArrayList.add(otherColoumn);
        newArrayList.add(otherColoumnType.toString());
        otherColoumns.add(newArrayList);
    }
    @Deprecated
    public void setOtherColoumn(String otherColoumn, Class c, String fkTableName, String fkcoloum) throws DataTypeExeption {
        DataType dType = DataTypeMapper.map(c);
        setOtherColoumn(otherColoumn,dType,fkTableName,fkcoloum);
    }
    public void setOtherColoumn(String otherColoumn, DataType otherColoumnType, String fkTableName, String fkcoloum){
        ArrayList<String> newArrayList= new ArrayList<String>();
        newArrayList.add(otherColoumn);
        newArrayList.add(otherColoumnType.toString());
        otherColoumns.add(newArrayList);
        ArrayList<String> fkArrayList= new ArrayList<String>();
        fkArrayList.add(otherColoumn);
        fkArrayList.add(fkTableName);
        fkArrayList.add(fkcoloum);
        foreignKeys.add(fkArrayList);
    }
    public String getForeignKeyAlterTable(String otherColoumn,String fkTableName,String fkcoloum){
        return String.format(alterTableForeignKey,tableName,otherColoumn,fkTableName,fkcoloum);
    }
    public ForeignKeyQuery getForeignKeyQuery(String coloumn,String fkTableName,String fkcoloum){
        return new ForeignKeyQuery(coloumn,tableName,fkTableName,fkcoloum);
    }
    public String dropTableQuery(){
        String returnQuery = String.format(dropTableTemplate,tableName);
        return returnQuery;
    }
    public String getQuery(){
        String returnQuery = String.format(queryTemplate,tableName,join());
        return returnQuery;
    };
    protected String join(){

        String modified;
        String returnQuery;
        if (autoincrement){
            returnQuery=autoincrementQuery;
        }else{
            returnQuery=nonAutoincrementQuery;
        }
        returnQuery = String.format(returnQuery,primaryKey,primaryKeyType);
        for (ArrayList<String> alist:otherColoumns){
            returnQuery+=comma;
            modified=String.format(template, alist.get(0),alist.get(1));
            returnQuery+=modified;
        }
        for (ArrayList<String> fklist:foreignKeys){
            returnQuery+=comma;
            modified=String.format(fkTemplate, fklist.get(0),fklist.get(1),fklist.get(2));
            returnQuery+=modified;
        }
        return returnQuery;
    }
    public int execute(JdbcTemplate jdbcTemplate){
        try{
            jdbcTemplate.execute(getQuery());
            return 1;
        }catch(Exception e){
            return 0;

        }
    }
    public IndexQueryGenerator getIndexQueryGeneratorForTable(){
        IndexQueryGenerator indexQueryGenerator = new IndexQueryGenerator();
        indexQueryGenerator.setTableName(tableName);
        return indexQueryGenerator;
    }
}
