package com.Airways.BAirways.Utility.MyLogger;

public class LoggerBuilder {
    private static AbstractLogger abstractLogger=null;
    public static AbstractLogger getLogger(){
        if (abstractLogger==null){
            abstractLogger = new PrintLogger();
        }
        return abstractLogger;
    }
    private static void setAbstractLogger(AbstractLogger logger){
        abstractLogger=logger;
    }
}
