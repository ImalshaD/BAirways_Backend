
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.RoleDTO;
import com.Airways.BAirways.Entity.Role;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;

public class RoleRepo extends Repo<RoleDTO> {



    public RoleRepo() {
        super(Role.tablename());
    }

    public RoleDTO getByRoleId(int roleId){
        if (existsByRoleId(roleId)){
            prepareGet();
            selectQuery.firstCondition(Role.roleid(),Operators.EQUAL,roleId);
            RoleDTO roleDTO = new RoleDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<RoleDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(roleDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByRoleId(int roleId){
        prepare();
        selectQuery.firstCondition(Role.roleid(), Operators.EQUAL,roleId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(RoleDTO dto) {
        return existsByRoleId(dto.getRole_id());
    }



    @Override
    public int insertRecord(RoleDTO dto) {

        prepareInsert();
        insertQuery.addValue(Role.title(),dto.getTitle());
        return insert();

    }

    @Override
    public int updateRecord(RoleDTO dtoOld, RoleDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getTitle()!=null && dtoNew.getTitle()!=null){
            if (dtoOld.getTitle()!=dtoNew.getTitle()){
                updateQuery.setField(Role.title(),dtoNew.getTitle());
            }
        }

        updateQuery.firstCondition(Role.roleid(),Operators.EQUAL,dtoOld.getRole_id());
        return update();

    }


    public int deleteByRoleId(int roleId){
        if (existsByRoleId(roleId)){
            prepareDelete();
            deleteQuery.firstCondition(Role.roleid(),Operators.EQUAL,roleId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(RoleDTO dto) {
        return deleteByRoleId(dto.getRole_id());
    }


}
