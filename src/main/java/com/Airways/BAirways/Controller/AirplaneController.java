package com.Airways.BAirways.Controller;


import com.Airways.BAirways.DTO.AirplaneDTO;
import com.Airways.BAirways.DTO.AirplaneModalDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.Service.AirplaneModalService;
import com.Airways.BAirways.Service.AirplaneService;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/airplane")
public class AirplaneController {
    private AirplaneService airplaneService = new AirplaneService();
    private AirplaneModalService airplaneModalService = new AirplaneModalService();
    @GetMapping(path="/planebyID/{planeid}")
    public ResponseDTO getSeatdetails(@PathVariable int planeid){
        ResponseDTO responseDTO= new ResponseDTO();
        AirplaneDTO airplaneDTO = airplaneService.getByPlaneID(planeid);
        AirplaneModalDTO airplaneModalDTO = airplaneModalService.getBymodelID(airplaneDTO.getModal_id());
        if (airplaneDTO==null){
            responseDTO.setCode(QueryStatus.FAILED.toString());
            responseDTO.setMessage("Internal server Error");
            responseDTO.setContext(null);
        }else{
            responseDTO.setCode(QueryStatus.SUCCESS.toString());
            responseDTO.setMessage("Details about airplane");
            responseDTO.setContext(airplaneModalDTO);
        }
        return responseDTO;
    }
}
