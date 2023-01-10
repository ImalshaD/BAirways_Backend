
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
        insertQuery.addValue(Airplane.getseatcolsfirstclass(),dto.getSeat_cols_firstclass());
        insertQuery.addValue(Airplane.getseatrowsfirstclass(),dto.getSeat_rows_firstclass());
        insertQuery.addValue(Airplane.getSeatcolsbusinessclass(),dto.getSeat_cols_businessclass());
        insertQuery.addValue(Airplane.getSeatrowsbusinessclass(),dto.getSeat_rows_businessclass());
        insertQuery.addValue(Airplane.getseatcolsfirstclass(),dto.getSeat_cols_firstclass());
        insertQuery.addValue(Airplane.getseatrowsfirstclass(),dto.getSeat_rows_firstclass());
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
        if (dtoOld.getSeat_cols_firstclass()!=0 && dtoNew.getSeat_cols_firstclass()!=0){
            if (dtoOld.getSeat_cols_firstclass()!=dtoNew.getSeat_cols_firstclass()){
                updateQuery.setField(Airplane.getseatcolsfirstclass(),dtoNew.getSeat_cols_firstclass());
            }
        }
        if (dtoOld.getSeat_rows_firstclass()!=0 && dtoNew.getSeat_rows_firstclass()!=0){
            if (dtoOld.getSeat_rows_firstclass()!=dtoNew.getSeat_rows_firstclass()){
                updateQuery.setField(Airplane.getseatrowsfirstclass(),dtoNew.getSeat_rows_firstclass());
            }
        }
        if (dtoOld.getSeat_cols_businessclass()!=0 && dtoNew.getSeat_cols_businessclass()!=0){
            if (dtoOld.getSeat_cols_businessclass()!=dtoNew.getSeat_cols_businessclass()){
                updateQuery.setField(Airplane.getSeatcolsbusinessclass(),dtoNew.getSeat_cols_businessclass());
            }
        }
        if (dtoOld.getSeat_rows_businessclass()!=0 && dtoNew.getSeat_rows_businessclass()!=0){
            if (dtoOld.getSeat_rows_businessclass()!=dtoNew.getSeat_rows_businessclass()){
                updateQuery.setField(Airplane.getSeatrowsbusinessclass(),dtoNew.getSeat_rows_businessclass());
            }
        }
        if (dtoOld.getSeat_cols_economyclass()!=0 && dtoNew.getSeat_cols_economyclass()!=0){
            if (dtoOld.getSeat_cols_economyclass()!=dtoNew.getSeat_cols_economyclass()){
                updateQuery.setField(Airplane.getSeatcolseconomyclass(),dtoNew.getSeat_cols_economyclass());
            }
        }
        if (dtoOld.getSeat_rows_economyclass()!=0 && dtoNew.getSeat_rows_economyclass()!=0){
            if (dtoOld.getSeat_rows_economyclass()!=dtoNew.getSeat_rows_economyclass()){
                updateQuery.setField(Airplane.getSeatrowseconomyclass(),dtoNew.getSeat_rows_economyclass());
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
