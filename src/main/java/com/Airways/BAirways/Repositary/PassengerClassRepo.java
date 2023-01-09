
package com.Airways.BAirways.Repositary;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.PassengerClassDTO;
import com.Airways.BAirways.Entity.PassengerClass;


import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class PassengerClassRepo extends Repo<PassengerClassDTO> {




    public PassengerClassRepo() {
        super(PassengerClass.tablename());
    }

    public PassengerClassDTO getByClassId(int classId){
        if (existsByClassId(classId)){
            prepareGet();
            selectQuery.firstCondition(PassengerClass.classid(),Operators.EQUAL,classId);
            PassengerClassDTO passengerclassDTO = new PassengerClassDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<PassengerClassDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(passengerclassDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByClassId(int classId){
        prepare();
        selectQuery.firstCondition(PassengerClass.classid(), Operators.EQUAL,classId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(PassengerClassDTO dto) {
        return existsByClassId(dto.getClass_id());
    }



    @Override
    public int insertRecord(PassengerClassDTO dto) {

        prepareInsert();
        insertQuery.addValue(PassengerClass.classname(),dto.getClass_name());
        return insert();

    }

    @Override
    public int updateRecord(PassengerClassDTO dtoOld, PassengerClassDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getClass_name()!=null && dtoNew.getClass_name()!=null){
            if (dtoOld.getClass_name()!=dtoNew.getClass_name()){
                updateQuery.setField(PassengerClass.classname(),dtoNew.getClass_name());
            }
        }

        updateQuery.firstCondition(PassengerClass.classid(),Operators.EQUAL,dtoOld.getClass_id());
        return update();

    }


    public int deleteByClassId(int classId){
        if (existsByClassId(classId)){
            prepareDelete();
            deleteQuery.firstCondition(PassengerClass.classid(),Operators.EQUAL,classId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(PassengerClassDTO dto) {
        return deleteByClassId(dto.getClass_id());
    }


}
