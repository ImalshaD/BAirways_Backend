package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.*;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Airport;
import com.Airways.BAirways.Entity.Location;
import com.Airways.BAirways.Repositary.LocationRepo;
import com.Airways.BAirways.Service.AirportService;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
