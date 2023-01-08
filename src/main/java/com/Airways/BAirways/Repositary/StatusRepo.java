
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.StatusDTO;
import com.Airways.BAirways.Entity.Status;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class StatusRepo extends Repo<StatusDTO> {



    public StatusRepo() {
        super(Status.tablename());
    }

    public StatusDTO getByStatusId(int statusId){
        if (existsByStatusId(statusId)){
            prepare();
            selectQuery.firstCondition(Status.statusid(),Operators.EQUAL,statusId);
            StatusDTO statusDTO = new StatusDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<StatusDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(statusDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByStatusId(int statusId){
        prepare();
        selectQuery.firstCondition(Status.statusid(), Operators.EQUAL,statusId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(StatusDTO dto) {
        return existsByStatusId(dto.getStatus_id());
    }



    @Override
    public int insertRecord(StatusDTO dto) {

        prepareInsert();
        insertQuery.addValue(Status.name(),dto.getName());
        return insert();

    }

    @Override
    public int updateRecord(StatusDTO dtoOld, StatusDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getName()!=null && dtoNew.getName()!=null){
            if (dtoOld.getName()!=dtoNew.getName()){
                updateQuery.setField(Status.name(),dtoNew.getName());
            }
        }

        updateQuery.firstCondition(Status.statusid(),Operators.EQUAL,dtoOld.getStatus_id());
        return update();

    }


    public int deleteByStatusId(int statusId){
        if (existsByStatusId(statusId)){
            prepareDelete();
            deleteQuery.firstCondition(Status.statusid(),Operators.EQUAL,statusId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(StatusDTO dto) {
        return deleteByStatusId(dto.getStatus_id());
    }


}
