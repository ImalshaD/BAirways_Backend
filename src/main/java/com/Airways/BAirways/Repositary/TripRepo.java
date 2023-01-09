
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.TripDTO;
import com.Airways.BAirways.Entity.Trip;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class TripRepo extends Repo<TripDTO> {



    public TripRepo() {
        super(Trip.tablename());
    }

    public TripDTO getByTripId(int tripId){
        if (existsByTripId(tripId)){
            prepareGet();
            selectQuery.firstCondition(Trip.tripid(),Operators.EQUAL,tripId);
            TripDTO tripDTO = new TripDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<TripDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(tripDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByTripId(int tripId){
        prepare();
        selectQuery.firstCondition(Trip.tripid(), Operators.EQUAL,tripId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(TripDTO dto) {
        return existsByTripId(dto.getTrip_id());
    }



    @Override
    public int insertRecord(TripDTO dto) {

        prepareInsert();
        insertQuery.addValue(Trip.departure(),dto.getDeparture());
        insertQuery.addValue(Trip.arrival(),dto.getArrival());
        insertQuery.addValue(Trip.routeid(),dto.getRoute_id());
        insertQuery.addValue(Trip.planeid(),dto.getPlane_id());
        insertQuery.addValue(Trip.statusid(),dto.getStatus_id());
        insertQuery.addValue(Trip.scheduleddate(),dto.getScheduled_date());
        return insert();

    }

    @Override
    public int updateRecord(TripDTO dtoOld, TripDTO dtoNew) {

        prepareInsert();
        if (dtoOld.getStatus_id()!=0 && dtoNew.getStatus_id()!=0){
            if (dtoOld.getStatus_id()!=dtoNew.getStatus_id()){
                updateQuery.setField(Trip.statusid(),dtoNew.getStatus_id());
            }
        }

        if (dtoOld.getDeparture()!=null && dtoNew.getDeparture()!=null){
            if (dtoOld.getDeparture()!=dtoNew.getDeparture()){
                updateQuery.setField(Trip.departure(),dtoNew.getDeparture());
            }
        }


        if (dtoOld.getArrival()!=null && dtoNew.getArrival()!=null){
            if (dtoOld.getArrival()!=dtoNew.getArrival()){
                updateQuery.setField(Trip.arrival(),dtoNew.getArrival());
            }
        }


        if (dtoOld.getRoute_id()!=0 && dtoNew.getRoute_id()!=0){
            if (dtoOld.getRoute_id()!=dtoNew.getRoute_id()){
                updateQuery.setField(Trip.routeid(),dtoNew.getRoute_id());
            }
        }


        if (dtoOld.getPlane_id()!=0 && dtoNew.getPlane_id()!=0){
            if (dtoOld.getPlane_id()!=dtoNew.getPlane_id()){
                updateQuery.setField(Trip.planeid(),dtoNew.getPlane_id());
            }
        }
        if (dtoOld.getScheduled_date()!=null && dtoNew.getScheduled_date()!=null){
            if (dtoOld.getScheduled_date()!=dtoNew.getScheduled_date()){
                updateQuery.setField(Trip.scheduleddate(),dtoNew.getScheduled_date());
            }
        }

        updateQuery.firstCondition(Trip.tripid(),Operators.EQUAL,dtoOld.getTrip_id());
        return update();
    }


    public int deleteByTripId(int tripId){
        if (existsByTripId(tripId)){
            prepareDelete();
            deleteQuery.firstCondition(Trip.tripid(),Operators.EQUAL,tripId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(TripDTO dto) {
        return deleteByTripId(dto.getTrip_id());
    }


}
