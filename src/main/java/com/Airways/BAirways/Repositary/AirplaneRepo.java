
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
        insertQuery.addValue(Airplane.modal(),dto.getModal());
        insertQuery.addValue(Airplane.manufacturedyear(),dto.getManufactured_year());
        insertQuery.addValue(Airplane.manufacturedcountry(),dto.getManufactured_country());
        insertQuery.addValue(Airplane.seatingcapacity(),dto.getSeating_capacity());
        insertQuery.addValue(Airplane.seatcols(),dto.getSeat_cols());
        insertQuery.addValue(Airplane.seatrows(),dto.getSeat_rows());
        return insert();
    }

    @Override
    public int updateRecord(AirplaneDTO dtoOld, AirplaneDTO dtoNew) {
        prepareInsert();
        if (dtoOld.getModal()!=null && dtoNew.getModal()!=null){
            if (dtoOld.getModal()!=dtoNew.getModal()){
                updateQuery.setField(Airplane.modal(),dtoNew.getModal());
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


        if (dtoOld.getSeating_capacity()!=0 && dtoNew.getSeating_capacity()!=0){
            if (dtoOld.getSeating_capacity()!=dtoNew.getSeating_capacity()){
                updateQuery.setField(Airplane.seatingcapacity(),dtoNew.getSeating_capacity());
            }
        }
        if (dtoOld.getSeat_cols()!=0 && dtoNew.getSeat_cols()!=0){
            if (dtoOld.getSeat_cols()!=dtoNew.getSeat_cols()){
                updateQuery.setField(Airplane.seatcols(),dtoNew.getSeat_cols());
            }
        }
        if (dtoOld.getSeat_rows()!=0 && dtoNew.getSeat_rows()!=0){
            if (dtoOld.getSeat_rows()!=dtoNew.getSeat_rows()){
                updateQuery.setField(Airplane.seatrows(),dtoNew.getSeat_rows());
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
