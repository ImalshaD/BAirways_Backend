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
public class Seat {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int seat_id;
    @MyColoumn @MyForiegnKey(table = Airplane.class,coloumn = Airplane.PLANEID)
    private int airplane_id;
    @MyColoumn @MyForiegnKey(table = PassengerClass.class,coloumn = PassengerClass.CLASSID)
    private int class_id;

    @MyColoumn
    private int col_id;
    @MyColoumn
    private int row_id;

    protected static final String SEATID="seat_id";
    protected static final String AIRPLANEID="airplane_id";
    protected static final String CLASSID="class_id";
    protected static final String tableName="Seat";
    protected static final String COLID="col_id";
    protected static final String ROWID="row_id";

    public static String seatid(){
        return SEATID;
    }


    public static String airplaneid(){
        return AIRPLANEID;
    }


    public static String classid(){
        return CLASSID;
    }


    public static String tablename(){
        return tableName;
    }
    public static String rowid(){
        return ROWID;
    }
    public static String colid(){
        return COLID;
    }

}
