package com.Airways.BAirways.Database;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class Template {
    private static JdbcTemplate jdbcTemplate = null;
    @Bean
    public org.springframework.jdbc.core.JdbcTemplate getJdbcTemplate(){
        if (jdbcTemplate==null) {
            Database x = Database.getInstance();
            jdbcTemplate = new JdbcTemplate(x.getDatasource());
        }
        return jdbcTemplate;
    }
}
