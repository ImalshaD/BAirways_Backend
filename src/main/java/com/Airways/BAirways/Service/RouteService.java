package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.RouteDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Route;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.MyLogger.PrintLogger;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RouteService {
    private Template template = new Template();
    private JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    private AbstractLogger logger = LoggerBuilder.getLogger();

    public int serchRoute(String from_iata,String to_iata) {
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setFields(Route.routeid());
        selectQuery.setTableName(Route.tablename());
        selectQuery.firstCondition(Route.from(), Operators.EQUAL,from_iata);
        selectQuery.joinCondition(JoinOperators.AND,Route.to(),Operators.EQUAL,to_iata);
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
        if (maps.size()>1){
            return -1;
        } else if (maps.size() == 0) {
            return -2;
        }else{
            Map<String,Object> map = maps.get(0);
            DTOMapper<RouteDTO> mapper = new DTOMapper<>();
            RouteDTO routeDTO = new RouteDTO();
            try {
                return mapper.maptoDTO(routeDTO, map).getRoute_id();
            }catch (Exception e){
                logger.log(e.toString());
                return -3;
            }
        }
    }
}
