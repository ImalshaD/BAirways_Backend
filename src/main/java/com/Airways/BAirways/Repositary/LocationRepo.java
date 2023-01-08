
package com.Airways.BAirways.Repositary;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.LocationDTO;
import com.Airways.BAirways.Entity.Location;


import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class LocationRepo extends Repo<LocationDTO> {



    public LocationRepo() {
        super(Location.tablename());
    }

    public LocationDTO getByLocationId(int locationId){
        if (existsByLocationId(locationId)){
            prepareGet();
            selectQuery.firstCondition(Location.locationid(),Operators.EQUAL,locationId);
            LocationDTO locationDTO = new LocationDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<LocationDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(locationDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByLocationId(int locationId){
        prepare();
        selectQuery.firstCondition(Location.locationid(), Operators.EQUAL,locationId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(LocationDTO dto) {
        return existsByLocationId(dto.getLocation_id());
    }



    @Override
    public int insertRecord(LocationDTO dto) {

        prepareInsert();

        insertQuery.addValue(Location.locationname(),dto.getLocation_name());
        insertQuery.addValue(Location.parent(),dto.getParent());
        return insert();

    }

    @Override
    public int updateRecord(LocationDTO dtoOld, LocationDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getLocation_name()!=null && dtoNew.getLocation_name()!=null){
            if (dtoOld.getLocation_name()!=dtoNew.getLocation_name()){
                updateQuery.setField(Location.locationname(),dtoNew.getLocation_name());
            }
        }


        if (dtoOld.getParent()!=0 && dtoNew.getParent()!=0){
            if (dtoOld.getParent()!=dtoNew.getParent()){
                updateQuery.setField(Location.parent(),dtoNew.getParent());
            }
        }

        updateQuery.firstCondition(Location.locationid(),Operators.EQUAL,dtoOld.getLocation_id());
        return update();

    }


    public int deleteByLocationId(int locationId){
        if (existsByLocationId(locationId)){
            prepareDelete();
            deleteQuery.firstCondition(Location.locationid(),Operators.EQUAL,locationId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(LocationDTO dto) {
        return deleteByLocationId(dto.getLocation_id());
    }


}
