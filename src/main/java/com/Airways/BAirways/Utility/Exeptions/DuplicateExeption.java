package com.Airways.BAirways.Utility.Exeptions;

public class DuplicateExeption extends Exception{
    private String msg;

    public DuplicateExeption(String msg) {
        super(msg);
        this.msg =msg;
    }
}
