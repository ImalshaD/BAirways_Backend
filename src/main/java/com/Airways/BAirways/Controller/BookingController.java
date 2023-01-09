package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.*;
import com.Airways.BAirways.Service.BookingService;
import com.Airways.BAirways.Service.Booking_LogService;
import com.Airways.BAirways.Service.RegisteredUserService;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/booking")
public class BookingController {
    private Booking_LogService booking_logService = new Booking_LogService();
    private BookingService bookingService = new BookingService();
    private RegisteredUserService registeredUserService = new RegisteredUserService();
    @PutMapping(path="/newBooking")
    public ResponseDTO newBooking(@RequestBody BookingRequestDTO reqdto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        RegisteredUserDTO dto = registeredUserService.getUserByuserName(username);
        List<PassengerDTO> list = reqdto.getList();
        Map<Object,Object> mapobj = new HashMap<>();
        for (PassengerDTO passengerDTO : list){
            int temp=bookingService.makeBooking(dto.getUser_id(),passengerDTO, reqdto.getTrip_id(), passengerDTO.getSeat_id());
            mapobj.put(passengerDTO.getSeat_id(),temp);
        }
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"Booking logs",mapobj);
    }
    @GetMapping(path="/getBookings")
    public ResponseDTO getBookings(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        RegisteredUserDTO currentUser = registeredUserService.getUserByuserName(username);
        List<Booking_logDTO> bookingLogs = booking_logService.getByUser(currentUser.getUser_id());
        List<BookingDTO> returnList = new ArrayList<>();
        for (Booking_logDTO dto : bookingLogs){
            returnList.add(bookingService.getByID(dto.getBooking_id()));
        }
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"List of bookings",returnList);
    }
}
