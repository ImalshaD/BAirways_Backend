
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.AssingnationDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.Assingnation;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class AssingnationRepo extends Repo<AssingnationDTO>{




    public AssingnationRepo() {
        super(Assingnation.tablename());
    }

    public AssingnationDTO getByAssingnationId(int assingnationId){
        if (existsByAssingnationId(assingnationId)){
            prepare();
            selectQuery.firstCondition(Assingnation.assingnationid(),Operators.EQUAL,assingnationId);
            AssingnationDTO assingnationDTO = new AssingnationDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<AssingnationDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(assingnationDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByAssingnationId(int assingnationId){
        prepare();
        selectQuery.firstCondition(Assingnation.assingnationid(), Operators.EQUAL,assingnationId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(AssingnationDTO dto) {
        return existsByAssingnationId(dto.getAssingnation_id());
    }



    @Override
    public int insertRecord(AssingnationDTO dto) {

        prepareInsert();

        insertQuery.addValue(Assingnation.staffid(),dto.getStaff_id());
        insertQuery.addValue(Assingnation.planeid(),dto.getPlane_id());
        insertQuery.addValue(Assingnation.roleid(),dto.getRole_id());
        return insert();

    }

    @Override
    public int updateRecord(AssingnationDTO dtoOld, AssingnationDTO dtoNew) {
        prepareInsert();
        if (dtoOld.getStaff_id()!=0 && dtoNew.getStaff_id()!=0){
            if (dtoOld.getStaff_id()!=dtoNew.getStaff_id()){
                updateQuery.setField(Assingnation.staffid(),dtoNew.getStaff_id());
            }
        }


        if (dtoOld.getPlane_id()!=0 && dtoNew.getPlane_id()!=0){
            if (dtoOld.getPlane_id()!=dtoNew.getPlane_id()){
                updateQuery.setField(Assingnation.planeid(),dtoNew.getPlane_id());
            }
        }


        if (dtoOld.getRole_id()!=0 && dtoNew.getRole_id()!=0){
            if (dtoOld.getRole_id()!=dtoNew.getRole_id()){
                updateQuery.setField(Assingnation.roleid(),dtoNew.getRole_id());
            }
        }

        updateQuery.firstCondition(Assingnation.assingnationid(),Operators.EQUAL,dtoOld.getAssingnation_id());
        return update();
    }


    public int deleteByAssingnationId(int assingnationId){
        if (existsByAssingnationId(assingnationId)){
            prepareDelete();
            deleteQuery.firstCondition(Assingnation.assingnationid(),Operators.EQUAL,assingnationId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(AssingnationDTO dto) {
        return deleteByAssingnationId(dto.getAssingnation_id());
    }


}
