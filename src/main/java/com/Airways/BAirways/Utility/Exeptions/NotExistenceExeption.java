package com.Airways.BAirways.Utility.Exeptions;

public class NotExistenceExeption extends Exception{
    private String messege;

    public NotExistenceExeption(String message) {
        super(message);
        this.messege = message;
    }
}
