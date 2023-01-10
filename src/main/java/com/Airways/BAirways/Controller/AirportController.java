package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.AirportDTO;
import com.Airways.BAirways.DTO.AirportShortDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.Service.AirportService;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/airport")
public class AirportController {
    private static AirportService airportService = new AirportService();

    @GetMapping(path="/airports")
    public ResponseDTO getAirports(){
        ResponseDTO responseDTO = new ResponseDTO();
        List<AirportDTO> list = airportService.getAirports();
        if (list==null){
            responseDTO.setCode(QueryStatus.FAILED.toString());
            responseDTO.setMessage("Error ocuured");
        }else{
            responseDTO.setCode(QueryStatus.SUCCESS.toString());
            responseDTO.setMessage("List of airports");
            responseDTO.setContext(list);
        }
        return responseDTO;
    }
    @GetMapping(path="/airportsShort")
    public ResponseDTO getAirportsShort(){
        ResponseDTO responseDTO = new ResponseDTO();
        List<AirportShortDTO> list = airportService.getAirportsShort();
        if (list==null){
            responseDTO.setCode(QueryStatus.FAILED.toString());
            responseDTO.setMessage("Error ocuured");
        }else{
            responseDTO.setCode(QueryStatus.SUCCESS.toString());
            responseDTO.setMessage("List of airports Short");
            responseDTO.setContext(list);
        }
        return responseDTO;
    }
}
