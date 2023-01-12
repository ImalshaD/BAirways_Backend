package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.CountryDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.Service.CountryService;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/country")
public class CountryController {
    private CountryService countryService = new CountryService();

    @GetMapping(path="/countrylist")
    public ResponseDTO countryList(){

        List<CountryDTO> lis = countryService.getCountryList();
        if (lis==null){
           return new ResponseDTO(QueryStatus.FAILED.toString(),"Internal server error",null);
        }else{
            return new ResponseDTO(QueryStatus.SUCCESS.toString(),"List of countries",lis);
        }
    }
}
