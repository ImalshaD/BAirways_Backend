package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.*;
import com.Airways.BAirways.Database.DataBaseFunctions.NewBooking;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Booking;
import com.Airways.BAirways.Repositary.BookingRepo;
import com.Airways.BAirways.Utility.Exeptions.DuplicateExeption;
import com.Airways.BAirways.Utility.Exeptions.NotExistenceExeption;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    private RegisteredUserService registeredUserService = new RegisteredUserService();
    private Booking_LogService booking_logService = new Booking_LogService();
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

    public int makeBooking(int user_id, PassengerDTO dto,int booking_id,int seat_id,int trip_id){
        int passengerId = passengerService.getPassengerIDifNotCreate(dto);
        String refNum = String.format(refNumformat,trip_id,seat_id,passengerId,user_id);
        String key = "key"+user_id;
        newBooking.setparams(passengerId,booking_id,user_id,refNum,key);
        return newBooking.call();
    }
    public Map<Object,Object> newBooking(List<PassengerDTO> listdto ,int trip_id) throws DuplicateExeption, NotExistenceExeption, NoSuchFieldException, IllegalAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        RegisteredUserDTO dto = registeredUserService.getUserByuserName(username);
        Map<Object,Object> mapobj = new HashMap<>();
        System.out.println(listdto.toString());
        for (PassengerDTO passengerDTO : listdto){
            System.out.println("11");
            BookingDTO bookingDTO = getBySeatIDTripID(passengerDTO.getSeat_id(),trip_id);
            System.out.println(bookingDTO.getBooking_id());
            int temp=makeBooking(dto.getUser_id(),passengerDTO,bookingDTO.getBooking_id(),passengerDTO.getSeat_id(),trip_id);
            System.out.println(temp);
            mapobj.put(passengerDTO.getSeat_id(),temp);
        }
        return mapobj;
    }
    public BookingDTO getByID(int bookingID){
        return bookingRepo.getByBookingId(bookingID);
    }

    public BookingDTO getBySeatIDTripID(int seat_id,int trip_id) throws NoSuchFieldException, IllegalAccessException, DuplicateExeption, NotExistenceExeption {
        SelectQueryPreparedStatementGenerator selectQuey = new SelectQueryPreparedStatementGenerator();
        selectQuey.setTableName(Booking.tablename());
        selectQuey.firstCondition(Booking.tripid(),Operators.EQUAL,trip_id);
        selectQuey.joinCondition(JoinOperators.AND,Booking.seatid(),Operators.EQUAL,seat_id);
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(selectQuey.getQuery(),selectQuey.getArguments());
        BookingDTO bookingDTO  = new BookingDTO();
        if (maps.size()>1){
            throw  new DuplicateExeption("DUPLICATE SEATID TRIPID IN booking table");
        } else if (maps.size() == 0) {
            throw new NotExistenceExeption("SEATID TRIPID doesn't exists in booking table");
        }
        DTOMapper<BookingDTO> dtoMapper = new DTOMapper();
        return dtoMapper.maptoDTO(bookingDTO,maps.get(0));
    }
    public List<BookingDTO> getBookingforCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        RegisteredUserDTO currentUser = registeredUserService.getUserByuserName(username);
        List<Booking_logDTO> bookingLogs = booking_logService.getByUser(currentUser.getUser_id());
        List<BookingDTO> returnList = new ArrayList<>();
        for (Booking_logDTO dto : bookingLogs){
            returnList.add(getByID(dto.getBooking_id()));
        }
        return returnList;
    }
}
