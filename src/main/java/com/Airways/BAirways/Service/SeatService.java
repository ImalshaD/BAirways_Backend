package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.SeatDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Booking;
import com.Airways.BAirways.Entity.Seat;
import com.Airways.BAirways.Utility.Exeptions.NotExistenceExeption;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class SeatService {
    private Template template = new Template();
    private JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    private AbstractLogger logger = LoggerBuilder.getLogger();
    private BookingService bookingService = new BookingService();
    public List<SeatDTO> getSeatbyTripID(int tripid) throws NotExistenceExeption {
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        List<Map<String,Object>> maps = bookingService.getMapofSeatsByTripID(tripid);
        if (maps.size()==0){
            throw new NotExistenceExeption("No seats for the tripID" + tripid);
        }
        List<Map<String,Object>> seatMaps;
        List<SeatDTO> seatList = new ArrayList<>();
        DTOMapper<SeatDTO> mapper = new DTOMapper<>();
        for (Map<String,Object> map : maps){
            selectQuery.reset();
            selectQuery.setTableName(Seat.tablename());
            selectQuery.firstCondition(Seat.seatid(),Operators.EQUAL,map.get(Seat.seatid()));
            seatMaps = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
            SeatDTO temp = new SeatDTO();
            if (map.get(Booking.passengerid())==null){
                temp.setAvailability(true);
            }else{
                temp.setAvailability(false);
            }
            try {
                seatList.add(mapper.maptoDTO(temp, seatMaps.get(0)));
            } catch (Exception e){
                logger.log(e.toString());
            }
        }
        return seatList;
    }
}
