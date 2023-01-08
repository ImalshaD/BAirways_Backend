package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.TripDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Trip;
import com.Airways.BAirways.Utility.Exeptions.DuplicateExeption;
import com.Airways.BAirways.Utility.Exeptions.NotExistenceExeption;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class TripService {
    private Template template = new Template();
    private JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    private AbstractLogger logger = LoggerBuilder.getLogger();
    private RouteService routeService = new RouteService();
    public List<TripDTO> serachByDestination(String fromIata, String toIata, LocalDate date) throws DuplicateExeption, NotExistenceExeption {
        int routeid=routeService.serchRoute(fromIata,toIata);
        List<TripDTO> returList = new ArrayList<TripDTO>();
        if (routeid==-1){
            logger.log("Duplicate records exists in Route Table fix this manually from "+fromIata+" to "+toIata);
            throw new DuplicateExeption("Duplicate routes exists fix this manually");
        } else if (routeid == -2) {
            logger.log("No records in the Route Table for from "+fromIata+" to "+toIata);
            throw  new NotExistenceExeption("No records");
        }else if (routeid==-3){
            logger.log("Internal exeption");
            return null;
        }else{
            SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
            selectQuery.setTableName(Trip.tablename());
            selectQuery.firstCondition(Trip.routeid(), Operators.EQUAL,routeid);
            selectQuery.joinCondition(JoinOperators.AND,Trip.scheduleddate(),Operators.EQUAL,date);
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
            DTOMapper<TripDTO> mapper = new DTOMapper<>();
            for (Map<String,Object> map : maps){
                TripDTO temp = new TripDTO();
                try {
                    temp = mapper.maptoDTO(temp, map);
                    returList.add(temp);
                }catch (Exception e){
                    logger.log(e.toString());
                }
            }
        }
        return returList;
    }
}
