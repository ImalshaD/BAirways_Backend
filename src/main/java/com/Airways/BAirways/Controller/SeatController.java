package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.AirplaneDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.DTO.SeatDTO;
import com.Airways.BAirways.DTO.TripDTO;
import com.Airways.BAirways.Service.AirplaneService;
import com.Airways.BAirways.Service.SeatService;
import com.Airways.BAirways.Service.TripService;
import com.Airways.BAirways.Utility.Exeptions.NotExistenceExeption;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/seat")
public class SeatController{
    private TripService tripService = new TripService();
    private SeatService seatService = new SeatService();
    private AirplaneService airplaneService = new AirplaneService();
    @GetMapping(path="/tripID/{tripid}")
    public ResponseDTO getSeatsByTripId(@PathVariable int tripid){
        TripDTO tripDTO = tripService.getByTripID(tripid);
        AirplaneDTO airplaneDTO = airplaneService.getByPlaneID(tripDTO.getPlane_id());
        try {
            List<SeatDTO> list = seatService.getSeatbyTripID(tripid);
            Map<String,Object> map = new HashMap<>();
            map.put("Coloumns",airplaneDTO.getSeat_cols());
            map.put("Rows",airplaneDTO.getSeat_rows());
            map.put("Seats",list);
            return new ResponseDTO(QueryStatus.SUCCESS.toString(),"Map of cols rows seats",map);
        } catch (NotExistenceExeption existenceExeption){
            return new ResponseDTO(QueryStatus.FAILED.toString(),"No seats for the trip",null);
        }
    }
}
