package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.BookingDTO;
import com.Airways.BAirways.DTO.PassengerDTO;
import com.Airways.BAirways.Database.DataBaseFunctions.NewBooking;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Booking;
import com.Airways.BAirways.Repositary.BookingRepo;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookingService {
    private static NewBooking newBooking = new NewBooking();
    private static String refNumformat = "%s-%s-%s-%s";
    private static Template template = new Template();
    private static JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    private PassengerService passengerService = new PassengerService();
    private AbstractLogger logger = LoggerBuilder.getLogger();
    private BookingRepo bookingRepo = new BookingRepo();
    public boolean checkSeatAvailability(int seat_id,int trip_id){
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setFields(Booking.passengerid());
        selectQuery.setTableName(Booking.tablename());
        selectQuery.firstCondition(Booking.tripid(), Operators.EQUAL,trip_id);
        selectQuery.joinCondition(JoinOperators.AND,Booking.tripid(),Operators.EQUAL,seat_id);
        try {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery(), selectQuery.getArguments());
            Map<String,Object> map = maps.get(0);
            if (map.get(Booking.passengerid())==null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            logger.log(e.toString());
        }
        return false;
    }
    public List<Map<String,Object>> getMapofSeatsByTripID(int tripid){
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setTableName(Booking.tablename());
        selectQuery.setFields(Booking.seatid(),Booking.passengerid());
        selectQuery.firstCondition(Booking.tripid(), Operators.EQUAL,tripid);
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
        return maps;
    }

    public int makeBooking(int user_id, PassengerDTO dto,int trip_id,int seat_id){
        int passengerId = passengerService.getPassengerIDifNotCreate(dto);
        String refNum = String.format(refNumformat,trip_id,seat_id,passengerId,user_id);
        String key = "key"+user_id;
        newBooking.setparams(seat_id,trip_id,passengerId,user_id,refNum,key);
        return newBooking.call();
    }
    public BookingDTO getByID(int bookingID){
        return bookingRepo.getByBookingId(bookingID);
    }
}
