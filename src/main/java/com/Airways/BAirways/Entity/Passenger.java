package com.Airways.BAirways.Entity;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import com.Airways.BAirways.Utility.Annotations.MyForiegnKey;
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
    @MyColoumn(unique = true)
    private String passport_number;
    @MyColoumn @MyForiegnKey(table = Country.class,coloumn = Country.COUNTRYID)
    private int nationality;
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
    @MyColoumn
    private int age;
    @MyColoumn
    private String address_line1;
    @MyColoumn
    private String address_line2;
    @MyColoumn
    private String address_line3;

    protected static final String PASSENGERID="passenger_id";
    protected static final String PASSPORTNUMBER="passport_number";
    protected static final String NATIONALITY="nationality";
    protected static final String FIRSTNAME="first_name";
    protected static final String LASTNAME="last_name";
    protected static final String EMAIL="email";
    protected static final String CONTACTNUMBER="contact_number";
    protected static final String BDAY="b_day";
    protected static final String AGE ="age";
    protected static final String ADDRESSLINE1="address_line1";
    protected static final String ADDRESSLINE2="address_line2";
    protected static final String ADDRESSLINE3="address_line3";
    protected static final String tableName="Passenger";

    public static String addressline1(){
        return ADDRESSLINE1;
    }
    public static String addressline2(){
        return ADDRESSLINE2;
    }
    public static String addressline3(){
        return ADDRESSLINE3;
    }
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

    public static String age(){
        return AGE;
    }

}
