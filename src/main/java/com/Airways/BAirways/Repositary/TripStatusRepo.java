
package com.Airways.BAirways.Repositary;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.TripStatusDTO;


import com.Airways.BAirways.Entity.TripStatus;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;

public class TripStatusRepo extends Repo<TripStatusDTO>{
    public TripStatusRepo() {
        super(TripStatus.tablename());
    }

    public TripStatusDTO getByTripstatusId(int tripstatusId){
        if (existsByTripstatusId(tripstatusId)){
            prepareGet();
            selectQuery.firstCondition(TripStatus.tripstatusid(),Operators.EQUAL,tripstatusId);
            TripStatusDTO tripstatusDTO = new TripStatusDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<TripStatusDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(tripstatusDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByTripstatusId(int tripstatusId){
        prepare();
        selectQuery.firstCondition(TripStatus.tripstatusid(), Operators.EQUAL,tripstatusId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(TripStatusDTO dto) {
        return existsByTripstatusId(dto.getTripstatus_id());
    }



    @Override
    public int insertRecord(TripStatusDTO dto) {
        prepareInsert();
        insertQuery.addValue(TripStatus.statusname(),dto.getStatus_name());
        return insert();
    }

    @Override
    public int updateRecord(TripStatusDTO dtoOld, TripStatusDTO dtoNew) {
        prepareInsert();

        if (dtoOld.getStatus_name()!=null && dtoNew.getStatus_name()!=null){
            if (dtoOld.getStatus_name()!=dtoNew.getStatus_name()){
                updateQuery.setField(TripStatus.statusname(),dtoNew.getStatus_name());
            }
        }

        updateQuery.firstCondition(TripStatus.tripstatusid(),Operators.EQUAL,dtoOld.getTripstatus_id());
        return update();
    }


    public int deleteByTripstatusId(int tripstatusId){
        if (existsByTripstatusId(tripstatusId)){
            prepareDelete();
            deleteQuery.firstCondition(TripStatus.tripstatusid(),Operators.EQUAL,tripstatusId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(TripStatusDTO dto) {
        return deleteByTripstatusId(dto.getTripstatus_id());
    }





}
