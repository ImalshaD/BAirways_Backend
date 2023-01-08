package com.Airways.BAirways.Utility.MyLogger;

public class PrintLogger extends AbstractLogger{
    @Override
    public void log(String logText) {
        System.out.println(logText);
    }
}
