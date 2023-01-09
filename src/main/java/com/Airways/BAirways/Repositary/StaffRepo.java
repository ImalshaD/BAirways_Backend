
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.StaffDTO;
import com.Airways.BAirways.Entity.Staff;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class StaffRepo extends Repo<StaffDTO> {



    public StaffRepo() {
        super(Staff.tablename());
    }

    public StaffDTO getByStaffId(int staffId){
        if (existsByStaffId(staffId)){
            prepareGet();
            selectQuery.firstCondition(Staff.staffid(),Operators.EQUAL,staffId);
            StaffDTO staffDTO = new StaffDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<StaffDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(staffDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByStaffId(int staffId){
        prepare();
        selectQuery.firstCondition(Staff.staffid(), Operators.EQUAL,staffId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(StaffDTO dto) {
        return existsByStaffId(dto.getStaff_id());
    }



    @Override
    public int insertRecord(StaffDTO dto) {

        prepareInsert();
        insertQuery.addValue(Staff.firstname(),dto.getFirst_name());
        insertQuery.addValue(Staff.lastname(),dto.getLast_name());
        insertQuery.addValue(Staff.gender(),dto.getGender());
        return insert();

    }

    @Override
    public int updateRecord(StaffDTO dtoOld, StaffDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getFirst_name()!=null && dtoNew.getFirst_name()!=null){
            if (dtoOld.getFirst_name()!=dtoNew.getFirst_name()){
                updateQuery.setField(Staff.firstname(),dtoNew.getFirst_name());
            }
        }


        if (dtoOld.getLast_name()!=null && dtoNew.getLast_name()!=null){
            if (dtoOld.getLast_name()!=dtoNew.getLast_name()){
                updateQuery.setField(Staff.lastname(),dtoNew.getLast_name());
            }
        }


        if (dtoOld.getGender()!=null && dtoNew.getGender()!=null){
            if (dtoOld.getGender()!=dtoNew.getGender()){
                updateQuery.setField(Staff.gender(),dtoNew.getGender());
            }
        }

        updateQuery.firstCondition(Staff.staffid(),Operators.EQUAL,dtoOld.getStaff_id());
        return update();

    }


    public int deleteByStaffId(int staffId){
        if (existsByStaffId(staffId)){
            prepareDelete();
            deleteQuery.firstCondition(Staff.staffid(),Operators.EQUAL,staffId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(StaffDTO dto) {
        return deleteByStaffId(dto.getStaff_id());
    }


}
