package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.AirplaneDTO;
import com.Airways.BAirways.Repositary.AirplaneRepo;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService {
    private AirplaneRepo repo = new AirplaneRepo();

    public AirplaneDTO getByPlaneID(int airplaneid){
        return repo.getByPlaneId(airplaneid);
    }
}
