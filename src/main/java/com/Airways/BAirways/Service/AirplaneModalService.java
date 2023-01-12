package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.AirplaneDTO;
import com.Airways.BAirways.DTO.AirplaneModalDTO;
import com.Airways.BAirways.Entity.AirplaneManufacture;
import com.Airways.BAirways.Repositary.AirplaneModalRepo;
import org.springframework.stereotype.Service;

@Service
public class AirplaneModalService {
     private static AirplaneModalRepo airplaneModalRepo = new AirplaneModalRepo();
     public AirplaneModalDTO getBymodelID(int model_id){
         return airplaneModalRepo.getByModalId(model_id);
     }
}
