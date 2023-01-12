
package com.Airways.BAirways.Repositary;
import com.Airways.BAirways.DTO.AirplaneManufactureDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.AirplaneManufacture;


import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


public class AirplaneManufactureRepo extends Repo<AirplaneManufactureDTO> {

    public AirplaneManufactureRepo() {
        super(AirplaneManufacture.getTableName());
    }

    public AirplaneManufactureDTO getByManufactureId(int manufactureId){
        if (existsByManufactureId(manufactureId)){
            prepare();
            selectQuery.firstCondition(AirplaneManufacture.manufactureid(),Operators.EQUAL,manufactureId);
            AirplaneManufactureDTO airplanemanufactureDTO = new AirplaneManufactureDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<AirplaneManufactureDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(airplanemanufactureDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByManufactureId(int manufactureId){
        prepare();
        selectQuery.firstCondition(AirplaneManufacture.manufactureid(), Operators.EQUAL,manufactureId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(AirplaneManufactureDTO dto) {
        return existsByManufactureId(dto.getManufacture_id());
    }



    @Override
    public int insertRecord(AirplaneManufactureDTO dto) {
        return 0;
    }

    @Override
    public int updateRecord(AirplaneManufactureDTO dtoOld, AirplaneManufactureDTO dtoNew) {
        return 0;
    }

    @Override
    public int deleteRecord(AirplaneManufactureDTO dto) {
        return 0;
    }
}
