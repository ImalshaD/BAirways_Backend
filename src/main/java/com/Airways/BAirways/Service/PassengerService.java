package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.PassengerDTO;
import com.Airways.BAirways.Repositary.PassengerRepo;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    private PassengerRepo passengerRepo = new PassengerRepo();

    public boolean checkExistence(String passportNumber){
        return passengerRepo.existsByPassPortNumber(passportNumber);
    }
    public PassengerDTO addNewPassenger(PassengerDTO passengerDTO){
        int x = passengerRepo.insertRecord(passengerDTO);
        return  passengerRepo.getByPassportID(passengerDTO.getPassport_number());
    }
    public int getPassengerIDifNotCreate(PassengerDTO dto){
        String passportNumber = dto.getPassport_number();
        if (checkExistence(passportNumber)){
            return passengerRepo.getByPassportID(passportNumber).getPassenger_id();
        }else{
            return addNewPassenger(dto).getPassenger_id();
        }
    }

    public PassengerDTO getByID(int id){
        return passengerRepo.getByPassengerId(id);
    }
}
