package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyPrimaryKey;
import com.Airways.BAirways.Utility.Annotations.MyTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MyTable
public class Passenger {
    @MyColoumn @MyPrimaryKey(autoIncrement = true)
    private int passenger_id;
    @MyColoumn
    private String passport_number;
    @MyColoumn
    private String nationality;
    @MyColoumn
    private String first_name;
    @MyColoumn
    private String last_name;
    @MyColoumn
    private String email;
    @MyColoumn
    private String contact_number;
    @MyColoumn
    private LocalDate b_day;

    protected static final String PASSENGERID="passenger_id";
    protected static final String PASSPORTNUMBER="passport_number";
    protected static final String NATIONALITY="nationality";
    protected static final String FIRSTNAME="first_name";
    protected static final String LASTNAME="last_name";
    protected static final String EMAIL="email";
    protected static final String CONTACTNUMBER="contact_number";
    protected static final String BDAY="b_day";
    protected static final String tableName="Passenger";

    public static String passengerid(){
        return PASSENGERID;
    }


    public static String passportnumber(){
        return PASSPORTNUMBER;
    }


    public static String nationality(){
        return NATIONALITY;
    }


    public static String firstname(){
        return FIRSTNAME;
    }


    public static String lastname(){
        return LASTNAME;
    }


    public static String email(){
        return EMAIL;
    }


    public static String contactnumber(){
        return CONTACTNUMBER;
    }


    public static String bday(){
        return BDAY;
    }


    public static String tablename(){
        return tableName;
    }

}
