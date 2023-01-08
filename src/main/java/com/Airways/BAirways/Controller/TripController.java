package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.RequestTripDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Service.TripService;
import com.Airways.BAirways.Utility.Exeptions.DuplicateExeption;
import com.Airways.BAirways.Utility.Exeptions.NotExistenceExeption;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/trip")
public class TripController {
    private static Template template = new Template();
    private static JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    private TripService tripService = new TripService();

    @GetMapping(path="/tripList")
    public ResponseDTO getTrips(@RequestBody RequestTripDTO dto){
        try{
            return new ResponseDTO(QueryStatus.SUCCESS.toString(),"List of Trips",tripService.serachByDestination(
                    dto.getFrom_iata(),dto.getTo_iata(),dto.getDate()
            ));
        }catch (DuplicateExeption de){
            return new ResponseDTO(QueryStatus.DUPLICATE.toString(),"Duplicate routes found",null);
        }catch (NotExistenceExeption nex){
            return new ResponseDTO(QueryStatus.FAILED.toString(),"No route found",null);
        }
    }

}
