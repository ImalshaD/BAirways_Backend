package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.BookingDTO;
import com.Airways.BAirways.DTO.BookingRequestDTO;
import com.Airways.BAirways.DTO.PassengerDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.Service.BookingService;
import com.Airways.BAirways.Service.Booking_LogService;
import com.Airways.BAirways.Service.RegisteredUserService;
import com.Airways.BAirways.Utility.Exeptions.DuplicateExeption;
import com.Airways.BAirways.Utility.Exeptions.NotExistenceExeption;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/booking")
public class BookingController {
    private Booking_LogService booking_logService = new Booking_LogService();
    private BookingService bookingService = new BookingService();
    private RegisteredUserService registeredUserService = new RegisteredUserService();
    @PostMapping(path="/newbooking")
    public ResponseDTO newBooking(@RequestBody BookingRequestDTO reqdto){
        System.out.println(reqdto.toString());
        System.out.println(reqdto.getList());
        try {
            Map<Object, Object> mapobj = bookingService.newBooking(reqdto.getList(), reqdto.getTrip_id());
            return new ResponseDTO(QueryStatus.SUCCESS.toString(),"Booking logs",mapobj);
        }catch (DuplicateExeption de){
            return new ResponseDTO(QueryStatus.DUPLICATE.toString(),"Booking_failed",null);
        }catch (NotExistenceExeption ne){
            return new ResponseDTO(QueryStatus.FAILED.toString(),"Booking_failed",null);
        }catch (Exception e){
            return new ResponseDTO(QueryStatus.FAILED.toString(),"Internal server error",null);
        }

    }
    @GetMapping(path="/getBookings")
    public ResponseDTO getBookings(){
        List<BookingDTO> returnList = bookingService.getBookingforCurrentUser();
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"List of bookings",returnList);
    }

    @GetMapping(path="/bookingpage")
    public ModelAndView bookingpage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booking");
        return modelAndView;
    }
}
