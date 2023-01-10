package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.BookingDTO;
import com.Airways.BAirways.DTO.BookingRequestDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.Service.BookingService;
import com.Airways.BAirways.Service.Booking_LogService;
import com.Airways.BAirways.Service.RegisteredUserService;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.web.bind.annotation.*;

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
        Map<Object,Object> mapobj = bookingService.newBooking(reqdto.getList(), reqdto.getTrip_id());
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"Booking logs",mapobj);
    }
    @GetMapping(path="/getBookings")
    public ResponseDTO getBookings(){
        List<BookingDTO> returnList = bookingService.getBookingforCurrentUser();
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"List of bookings",returnList);
    }
}
