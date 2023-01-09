
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.PassengerDTO;
import com.Airways.BAirways.Entity.Passenger;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class PassengerRepo extends Repo<PassengerDTO> {



    public PassengerRepo() {
        super(Passenger.tablename());
    }

    public PassengerDTO getByPassengerId(int passengerId){
        if (existsByPassengerId(passengerId)){
            prepareGet();
            selectQuery.firstCondition(Passenger.passengerid(),Operators.EQUAL,passengerId);
            PassengerDTO passengerDTO = new PassengerDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<PassengerDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(passengerDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public PassengerDTO getByPassportID(String passPort){
        if (existsByPassPortNumber(passPort)){
            prepareGet();
            selectQuery.firstCondition(Passenger.passportnumber(),Operators.EQUAL,passPort);
            PassengerDTO passengerDTO = new PassengerDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<PassengerDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(passengerDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByPassengerId(int passengerId){
        prepare();
        selectQuery.firstCondition(Passenger.passengerid(), Operators.EQUAL,passengerId);
        return exists();
    }
    public boolean existsByPassPortNumber(String passportNumber){
        prepare();
        selectQuery.firstCondition(Passenger.passportnumber(), Operators.EQUAL,passportNumber);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(PassengerDTO dto) {
        return existsByPassengerId(dto.getPassenger_id());
    }



    @Override
    public int insertRecord(PassengerDTO dto) {


        prepareInsert();

        insertQuery.addValue(Passenger.passportnumber(),dto.getPassport_number());
        insertQuery.addValue(Passenger.nationality(),dto.getNationality());
        insertQuery.addValue(Passenger.firstname(),dto.getFirst_name());
        insertQuery.addValue(Passenger.lastname(),dto.getLast_name());
        insertQuery.addValue(Passenger.email(),dto.getEmail());
        insertQuery.addValue(Passenger.contactnumber(),dto.getContact_number());
        insertQuery.addValue(Passenger.bday(),dto.getB_day());
        return insert();


    }

    @Override
    public int updateRecord(PassengerDTO dtoOld, PassengerDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getPassport_number()!=null && dtoNew.getPassport_number()!=null){
            if (dtoOld.getPassport_number()!=dtoNew.getPassport_number()){
                updateQuery.setField(Passenger.passportnumber(),dtoNew.getPassport_number());
            }
        }


        if (dtoOld.getNationality()!=null && dtoNew.getNationality()!=null){
            if (dtoOld.getNationality()!=dtoNew.getNationality()){
                updateQuery.setField(Passenger.nationality(),dtoNew.getNationality());
            }
        }


        if (dtoOld.getFirst_name()!=null && dtoNew.getFirst_name()!=null){
            if (dtoOld.getFirst_name()!=dtoNew.getFirst_name()){
                updateQuery.setField(Passenger.firstname(),dtoNew.getFirst_name());
            }
        }


        if (dtoOld.getLast_name()!=null && dtoNew.getLast_name()!=null){
            if (dtoOld.getLast_name()!=dtoNew.getLast_name()){
                updateQuery.setField(Passenger.lastname(),dtoNew.getLast_name());
            }
        }


        if (dtoOld.getEmail()!=null && dtoNew.getEmail()!=null){
            if (dtoOld.getEmail()!=dtoNew.getEmail()){
                updateQuery.setField(Passenger.email(),dtoNew.getEmail());
            }
        }


        if (dtoOld.getContact_number()!=null && dtoNew.getContact_number()!=null){
            if (dtoOld.getContact_number()!=dtoNew.getContact_number()){
                updateQuery.setField(Passenger.contactnumber(),dtoNew.getContact_number());
            }
        }


        if (dtoOld.getB_day()!=null && dtoNew.getB_day()!=null){
            if (dtoOld.getB_day()!=dtoNew.getB_day()){
                updateQuery.setField(Passenger.bday(),dtoNew.getB_day());
            }
        }

        updateQuery.firstCondition(Passenger.passengerid(),Operators.EQUAL,dtoOld.getPassenger_id());
        return update();
    }


    public int deleteByPassengerId(int passengerId){
        if (existsByPassengerId(passengerId)){
            prepareDelete();
            deleteQuery.firstCondition(Passenger.passengerid(),Operators.EQUAL,passengerId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(PassengerDTO dto) {
        return deleteByPassengerId(dto.getPassenger_id());
    }


}
