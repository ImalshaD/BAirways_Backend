package com.Airways.BAirways.Utility.QueryHelper;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class IntegerRowMapper implements RowMapper<Integer> {
    private String name;
    @Override
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Integer(rs.getInt(name));
    }
}
