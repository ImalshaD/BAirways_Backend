
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.AirplaneModalDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.AirplaneModal;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class AirplaneModalRepo extends Repo<AirplaneModalDTO> {
    private static final AirplaneModal airplanemodal = new AirplaneModal();

    public AirplaneModalRepo() {
        super(AirplaneModal.getTableName());
    }


    public AirplaneModalDTO getByModalId(int modalId){
        if (existsByModalId(modalId)){
            prepareGet();
            selectQuery.firstCondition(AirplaneModal.modalid(),Operators.EQUAL,modalId);
            AirplaneModalDTO airplanemodalDTO = new AirplaneModalDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<AirplaneModalDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(airplanemodalDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByModalId(int modalId){
        prepare();
        selectQuery.firstCondition(AirplaneModal.modalid(), Operators.EQUAL,modalId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(AirplaneModalDTO dto) {
        return existsByModalId(dto.getModal_id());
    }



    @Override
    public int insertRecord(AirplaneModalDTO dto) {
        return 0;
    }

    @Override
    public int updateRecord(AirplaneModalDTO dtoOld, AirplaneModalDTO dtoNew) {
        return 0;
    }

    @Override
    public int deleteRecord(AirplaneModalDTO dto) {
        return 0;
    }
}
