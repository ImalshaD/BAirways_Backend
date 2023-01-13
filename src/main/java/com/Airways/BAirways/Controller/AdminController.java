package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.*;
import com.Airways.BAirways.Database.DataBaseFunctions.*;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/admin")
public class AdminController {
    @GetMapping(path="/adminview")
    public ModelAndView adminview(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage.html");
        return modelAndView;
    }

    @PostMapping(path="/pastFlights")
    public ResponseDTO pastFlights(@RequestBody ReportRequestsDTO reqdto){
        PastFlight pastFlight = new PastFlight();

        pastFlight.setParams(reqdto.getFrom_iata(),reqdto.getTo_iata());
        List<Map<String,Object>> maps = pastFlight.callfunction();

        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"Past Flights",maps);
    }
    @GetMapping(path="/revenue")
    public ResponseDTO revenue(){
        Revenue revenue = new Revenue();
        List<Map<String ,Object>> maps = revenue.callfunction();
        System.out.println(maps.toString());
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"Revenue",maps);
    }
    @PostMapping(path="/checkbydesti")
    public ResponseDTO checkbydesti(@RequestBody RequestDataDTO dto){
        CheckByDesti checkByDesti = new CheckByDesti();
        checkByDesti.setParams(dto.getFrom_date(),dto.getTo_date(), dto.getDestination());
        int x = checkByDesti.call();
        Map<String,Object> map = new HashMap<>();
        map.put("desti", dto.getDestination());
        map.put("number",x);
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"I am tired",map);
    }
    @PostMapping(path="/bookingsbyType")
    public ResponseDTO bookingsByType(@RequestBody RequestDataDTO dto){
        NumberOfBookingByType numberOfBookingByType = new NumberOfBookingByType();
        numberOfBookingByType.setParams(dto.getFrom_date(),dto.getTo_date());
        List<Map<String,Object>> maps = numberOfBookingByType.callresults();
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"I am exhausted now",maps);
    }
    @PostMapping(path="/passengersbyage")
    public ResponseDTO passengersByage(@RequestBody RequestPassengerDTO dto){
        PassengerByFlightID passengerByFlightID = new PassengerByFlightID();
        passengerByFlightID.setParams(dto.getFlight_id(), dto.isOver18());
        List<Map<String,Object>> maps = passengerByFlightID.callfunction();
        return new ResponseDTO(QueryStatus.SUCCESS.toString(),"I am done!",maps);
    }
}
