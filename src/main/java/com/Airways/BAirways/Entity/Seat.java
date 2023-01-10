package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.*;
import com.Airways.BAirways.Utility.QueryHelper.Query.Trigger;
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


    @MyTrigger
    public static Trigger seat_creator(){
        String name = "seat_creator";
        String query = """
                create trigger %s
                after insert on airplane for each row
                BEGIN
                	declare firstClass_cols int default 0;
                    declare firstClass_rows int default 0;
                	declare buisnessClass_cols int default 0;
                    declare buisnessClass_rows int default 0;
                    declare econClass_cols int default 0;
                    declare econClass_rows int default 0;
                    declare first_i int default 0;
                    declare buisness_i int default 0;
                    declare econ_j int default 0;
                    declare first_j int default 0;
                    declare buisness_j int default 0;
                    declare econ_i int default 0;
                    set firstClass_cols = NEW.seat_cols_firstclass;
                    set firstClass_rows = NEW.seat_rows_firstclass;
                    set buisnessClass_cols = NEW.seat_cols_businessclass;
                    set buisnessClass_rows = NEW.seat_rows_businessclass;
                    set econClass_cols = NEW.seat_cols_economyclass;
                    set econClass_rows = NEW.seat_rows_economyclass;
                    while first_j < firstClass_cols DO
                		SET first_j = first_j+1;
                        SET first_i = 0;
                		while first_i < firstClass_rows Do
                			SET first_i = first_i+1;
                			insert into seat(airplane_id,class_id,col_id,row_id) values (NEW.plane_id,1,first_j,first_i);
                		END while;
                	end while;
                    while econ_j < econClass_cols DO
                		SET econ_j = econ_j+1;
                        SET econ_i = 0;
                		while econ_i < econClass_rows Do
                			SET econ_i = econ_i+1;
                			insert into seat(airplane_id,class_id,col_id,row_id) values (NEW.plane_id,'3',econ_j,econ_i);
                		END while;
                	end while;
                    while buisness_j < buisnessClass_cols DO
                		SET buisness_j = buisness_j+1;
                        SET buisness_i = 0;
                		while buisness_i < buisnessClass_rows Do
                			SET buisness_i = buisness_i+1;
                			insert into seat(airplane_id,class_id,col_id,row_id) values (NEW.plane_id,'2',buisness_j,buisness_i);
                		END while;
                	end while;
                end ;
                """;
        return new Trigger(name,String.format(query,name));
    }

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
