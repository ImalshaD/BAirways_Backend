package com.Airways.BAirways.Database;

import lombok.Data;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

public class Database {
    private static String dBname="testing2";

    private static Database instance = null;


    public static String getdBname() {
        return dBname;
    }

    public static Database getInstance(){
        if (instance==null){
            instance= new Database();
        }
        return instance;
    }
    @Bean
    public javax.sql.DataSource getDatasource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/testing2?createDatabaseIfNotExist=true");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("#Damkith123");
        return dataSourceBuilder.build();
    }
}
