
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.AirportDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.Airport;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class AirportRepo extends Repo<AirportDTO>{




    public AirportRepo() {
        super(Airport.tablename());
    }


    public AirportDTO getByIataCode(String iataCode){
        if (existsByIataCode(iataCode)){
            prepare();
            selectQuery.firstCondition(Airport.iatacode(),Operators.EQUAL,iataCode);
            AirportDTO airportDTO = new AirportDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<AirportDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(airportDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }



    public boolean existsByIataCode(String IATACode){
        prepare();
        selectQuery.firstCondition(Airport.iatacode(),Operators.EQUAL,IATACode);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(AirportDTO dto) {
        return existsByIataCode(dto.getIATA_Code());
    }
    @Override
    public int insertRecord(AirportDTO dto) {

        prepareInsert();
        insertQuery.addValue(Airport.iatacode(),dto.getIATA_Code());
        insertQuery.addValue(Airport.locationhierarchyid(),dto.getLocation_hierarchy_id());
        insertQuery.addValue(Airport.timezone(),dto.getTime_zone());
        return insert();

    }

    @Override
    public int updateRecord(AirportDTO dtoOld, AirportDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getLocation_hierarchy_id()!=0 && dtoNew.getLocation_hierarchy_id()!=0){
            if (dtoOld.getLocation_hierarchy_id()!=dtoNew.getLocation_hierarchy_id()){
                updateQuery.setField(Airport.locationhierarchyid(),dtoNew.getLocation_hierarchy_id());
            }
        }


        if (dtoOld.getTime_zone()!=0 && dtoNew.getTime_zone()!=0){
            if (dtoOld.getTime_zone()!=dtoNew.getTime_zone()){
                updateQuery.setField(Airport.timezone(),dtoNew.getTime_zone());
            }
        }

        updateQuery.firstCondition(Airport.iatacode(),Operators.EQUAL,dtoOld.getIATA_Code());
        return update();

    }


    public int deleteByIataCode(String IATACode){
        if (existsByIataCode(IATACode)) {
            prepareDelete();
            deleteQuery.firstCondition(Airport.iatacode(), Operators.EQUAL, IATACode);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(AirportDTO dto) {
        return deleteByIataCode(dto.getIATA_Code());
    }


}
