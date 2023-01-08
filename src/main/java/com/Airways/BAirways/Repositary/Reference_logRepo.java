
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.Reference_logDTO;
import com.Airways.BAirways.Entity.Reference_log;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class Reference_logRepo extends Repo<Reference_logDTO> {



    public Reference_logRepo() {
        super(Reference_log.tablename());
    }

    public Reference_logDTO getByReferenceNum(String referenceNum){
        if (existsByReferenceNum(referenceNum)){
            prepare();
            selectQuery.firstCondition(Reference_log.referencenum(),Operators.EQUAL,referenceNum);
            Reference_logDTO reference_logDTO = new Reference_logDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<Reference_logDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(reference_logDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByReferenceNum(String referenceNum){
        prepare();
        selectQuery.firstCondition(Reference_log.referencenum(), Operators.EQUAL,referenceNum);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(Reference_logDTO dto) {
        return existsByReferenceNum(dto.getReference_num());
    }



    @Override
    public int insertRecord(Reference_logDTO dto) {

        prepareInsert();
        insertQuery.addValue(Reference_log.bookingid(),dto.getBooking_id());
        insertQuery.addValue(Reference_log.referencekey(),dto.getReferenceKey());
        insertQuery.addValue(Reference_log.referencenum(),dto.getReference_num());
        return insert();

    }

    @Override
    public int updateRecord(Reference_logDTO dtoOld, Reference_logDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getBooking_id()!=0 && dtoNew.getBooking_id()!=0){
            if (dtoOld.getBooking_id()!=dtoNew.getBooking_id()){
                updateQuery.setField(Reference_log.bookingid(),dtoNew.getBooking_id());
            }
        }


        if (dtoOld.getReferenceKey()!=null && dtoNew.getReferenceKey()!=null){
            if (dtoOld.getReferenceKey()!=dtoNew.getReferenceKey()){
                updateQuery.setField(Reference_log.referencekey(),dtoNew.getReferenceKey());
            }
        }

        updateQuery.firstCondition(Reference_log.referencenum(),Operators.EQUAL,dtoOld.getReference_num());
        return update();

    }


    public int deleteByReferenceNum(String referenceNum){
        if (existsByReferenceNum(referenceNum)){
            prepareDelete();
            deleteQuery.firstCondition(Reference_log.referencenum(),Operators.EQUAL,referenceNum);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(Reference_logDTO dto) {
        return deleteByReferenceNum(dto.getReference_num());
    }


}
