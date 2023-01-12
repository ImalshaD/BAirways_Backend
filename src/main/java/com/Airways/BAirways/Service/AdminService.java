package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.PassengerDTO;
import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Passenger;
import com.Airways.BAirways.Entity.Trip;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    private Template template = new Template();
    private JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    public List<PassengerDTO> getUsers(int plane_id,boolean under18){
        return null;
    }
}
