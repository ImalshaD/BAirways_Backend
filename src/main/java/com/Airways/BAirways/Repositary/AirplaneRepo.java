
package com.Airways.BAirways.Repositary;
import com.Airways.BAirways.DTO.AirplaneDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.Airplane;



import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import java.util.List;
import java.util.Map;

public class AirplaneRepo extends Repo<AirplaneDTO>{


    public AirplaneRepo() {
        super(Airplane.getTableName());
    }


    public AirplaneDTO getByPlaneId(int planeId){
        if (existsByPlaneId(planeId)){
            prepareGet();
            selectQuery.firstCondition(Airplane.planeid(), Operators.EQUAL,planeId);
            AirplaneDTO airplaneDTO = new AirplaneDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<AirplaneDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(airplaneDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean existsByPlaneId(int planeId){
        prepare();
        selectQuery.firstCondition(Airplane.planeid(), Operators.EQUAL,planeId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(AirplaneDTO dto) {
        return existsByPlaneId(dto.getPlane_id());
    }

    @Override
    public int insertRecord(AirplaneDTO dto) {
        prepareInsert();
        insertQuery.addValue(Airplane.modalid(),dto.getModal_id());
        insertQuery.addValue(Airplane.manufacturedyear(),dto.getManufactured_year());
        insertQuery.addValue(Airplane.manufacturedcountry(),dto.getManufactured_country());
        return insert();
    }

    @Override
    public int updateRecord(AirplaneDTO dtoOld, AirplaneDTO dtoNew) {
        prepareInsert();
        if (dtoOld.getModal_id()!=0 && dtoNew.getModal_id()!=0){
            if (dtoOld.getModal_id()!=dtoNew.getModal_id()){
                updateQuery.setField(Airplane.modalid(),dtoNew.getModal_id());
            }
        }


        if (dtoOld.getManufactured_year()!=0 && dtoNew.getManufactured_year()!=0){
            if (dtoOld.getManufactured_year()!=dtoNew.getManufactured_year()){
                updateQuery.setField(Airplane.manufacturedyear(),dtoNew.getManufactured_year());
            }
        }


        if (dtoOld.getManufactured_country()!=null && dtoNew.getManufactured_country()!=null){
            if (dtoOld.getManufactured_country()!=dtoNew.getManufactured_country()){
                updateQuery.setField(Airplane.manufacturedcountry(),dtoNew.getManufactured_country());
            }
        }
        updateQuery.firstCondition(Airplane.planeid(),Operators.EQUAL,dtoOld.getPlane_id());
        return update();

    }


    public int deleteByPlaneId(int planeId){
        if (existsByPlaneId(planeId)) {
            prepareDelete();
            deleteQuery.firstCondition(Airplane.planeid(), Operators.EQUAL, planeId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(AirplaneDTO dto) {
        return deleteByPlaneId(dto.getPlane_id());
    }




}
