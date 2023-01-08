package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyForiegnKey;
import com.Airways.BAirways.Utility.Annotations.MyPrimaryKey;
import com.Airways.BAirways.Utility.Annotations.MyTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MyTable
public class Assingnation {

    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int assingnation_id;
    @MyColoumn @MyForiegnKey(table = Staff.class,coloumn = Staff.STAFFID)
    private int staff_id;
    @MyColoumn @MyForiegnKey(table= Airplane.class,coloumn = Airplane.PLANEID)
    private int plane_id;
    @MyColoumn @MyForiegnKey(table= Role.class,coloumn = Role.ROLEID)
    private int role_id;

    protected static final String ASSINGNATIONID="assingnation_id";
    protected static final String STAFFID="staff_id";
    protected static final String PLANEID="palne_id";
    protected static final String ROLEID="role_id";
    protected static final String tableName="Assingnation";
    public static String assingnationid(){
        return ASSINGNATIONID;
    }


    public static String staffid(){
        return STAFFID;
    }


    public static String planeid(){
        return PLANEID;
    }


    public static String roleid(){
        return ROLEID;
    }
    public static String tablename(){
        return tableName;
    }
}
