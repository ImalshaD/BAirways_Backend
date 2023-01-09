package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.Booking_logDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Booking_log;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Booking_LogService {
    private static Template template = new Template();
    private static AbstractLogger logger = LoggerBuilder.getLogger();
    private static JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    public List<Booking_logDTO> getByUser(int user_id){
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setTableName(Booking_log.tablename());
        selectQuery.firstCondition(Booking_log.userid(), Operators.EQUAL,user_id);
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
        List<Booking_logDTO> returnList = new ArrayList<>();
        DTOMapper<Booking_logDTO> mapper = new DTOMapper<>();
        for (Map<String,Object> map : maps){
            Booking_logDTO temp = new Booking_logDTO();
            try {
                returnList.add(mapper.maptoDTO(temp, map));
            }catch (Exception e){
                logger.log(e.toString());
            }
        }
        return returnList;
    }
}
