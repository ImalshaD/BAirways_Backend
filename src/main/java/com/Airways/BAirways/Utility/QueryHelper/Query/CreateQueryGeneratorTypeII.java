package com.Airways.BAirways.Utility.QueryHelper.Query;

import com.Airways.BAirways.Utility.Exeptions.DataTypeExeption;
import com.Airways.BAirways.Utility.QueryHelper.DataTypes.DataType;
import com.Airways.BAirways.Utility.QueryHelper.DataTypes.DataTypeMapper;

import java.util.ArrayList;

public class CreateQueryGeneratorTypeII extends CreateQueryGenerator{
    private ArrayList<String> primaryKeyFields;
    private static final String autoIncrementField="%s %s AUTO_INCREMENT";
    private static final String pkTemplate="PRIMARY KEY(%s)";
    private static  final String uniqueTemplate="UNIQUE (%s)";
    private ArrayList<String> uniqueFields;
    private ArrayList<ArrayList<String>> autoIncrementFields;
    @Override
    public void setPrimaryKey(String pk, DataType pkType, Boolean autoincrement){
        primaryKeyFields.add(pk);
        if (autoincrement){
            ArrayList<String> temp= new ArrayList<String>();
            temp.add(pk);
            temp.add(pkType.toString());
            autoIncrementFields.add(temp);
        }else{
            setOtherColoumn(pk,pkType);
        }
    }
    @Override
    public void setOtherColoumn(String otherColoumn, DataType dataType,boolean unique){
        setOtherColoumn(otherColoumn,dataType);
        if (unique==true) {
            uniqueFields.add(otherColoumn);
        }
    }
    public CreateQueryGeneratorTypeII() {
        super();
        primaryKeyFields=new ArrayList<String>();
        autoIncrementFields=new ArrayList<ArrayList<String>>();
        uniqueFields=new ArrayList<String>();
    }
    @Override
    public void reset(){
        super.reset();
        primaryKeyFields=new ArrayList<String>();
        autoIncrementFields=new ArrayList<ArrayList<String>>();
        uniqueFields=new ArrayList<String>();
    }
    @Override
    protected String join(){
        ArrayList<String> temp = new ArrayList<String>();
        for (ArrayList<String> arry : autoIncrementFields){
            temp.add(String.format(autoIncrementField,arry.get(0),arry.get(1)));
        }
        for (ArrayList<String> arry : otherColoumns){
            temp.add(String.format(template,arry.get(0),arry.get(1)));
        }
        temp.add(String.format(pkTemplate,String.join(comma,primaryKeyFields)));
        for (ArrayList<String> arry : foreignKeys){
            temp.add(String.format(fkTemplate,arry.get(0),arry.get(1),arry.get(2)));
        }
        for (String s: uniqueFields){
            temp.add(String.format(uniqueTemplate,s));
        }
        return String.join(comma,temp);
    }
}
