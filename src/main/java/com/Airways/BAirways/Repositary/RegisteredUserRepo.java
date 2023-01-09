
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Entity.RegisteredUser;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class RegisteredUserRepo extends Repo<RegisteredUserDTO> {



    public RegisteredUserRepo() {
        super(RegisteredUser.getTableName());
    }

    public boolean existsByUserId(int userId){
        prepareGet();
        selectQuery.firstCondition(RegisteredUser.id(), Operators.EQUAL,userId);
        return exists();
    }
    public boolean existsByUserName(String username){
        prepare();
        selectQuery.firstCondition(RegisteredUser.username(), Operators.EQUAL,username);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(RegisteredUserDTO dto) {
        return existsByUserId(dto.getUser_id());
    }

    public RegisteredUserDTO getByUserId(int userId){
        if (existsByUserId(userId)){
            prepareGet();
            selectQuery.firstCondition(RegisteredUser.id(),Operators.EQUAL,userId);
            RegisteredUserDTO registereduserDTO = new RegisteredUserDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<RegisteredUserDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(registereduserDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public RegisteredUserDTO getByUserName(String username){
        if (existsByUserName(username)){
            prepareGet();
            selectQuery.firstCondition(RegisteredUser.username(),Operators.EQUAL,username);
            RegisteredUserDTO registereduserDTO = new RegisteredUserDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<RegisteredUserDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(registereduserDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }



    @Override
    public int insertRecord(RegisteredUserDTO dto) {

        prepareInsert();
        insertQuery.addValue(RegisteredUser.username(),dto.getUser_name());
        insertQuery.addValue(RegisteredUser.firstname(),dto.getFirst_name());
        insertQuery.addValue(RegisteredUser.lastname(),dto.getLast_name());
        insertQuery.addValue(RegisteredUser.password(),dto.getPassword());
        insertQuery.addValue(RegisteredUser.type_id(),dto.getType_id());
        insertQuery.addValue(RegisteredUser.email(),dto.getEmail());
        return insert();

    }

    @Override
    public int updateRecord(RegisteredUserDTO dtoOld, RegisteredUserDTO dtoNew) {
        if (dtoOld.getUser_id()==0){
            return 0;
        } else if (dtoNew.getUser_id() == 0) {
            return 0;
        }else if(dtoOld.getUser_id()!=dtoNew.getUser_id()){
            return 0;
        } else if (existsByUserId(dtoOld.getUser_id()) == false) {
            return 0;
        }
        prepareInsert();

        if (dtoOld.getUser_name()!=null && dtoNew.getUser_name()!=null){
            if (dtoOld.getUser_name()!=dtoNew.getUser_name()){
                updateQuery.setField(RegisteredUser.username(),dtoNew.getUser_name());
            }
        }


        if (dtoOld.getFirst_name()!=null && dtoNew.getFirst_name()!=null){
            if (dtoOld.getFirst_name()!=dtoNew.getFirst_name()){
                updateQuery.setField(RegisteredUser.firstname(),dtoNew.getFirst_name());
            }
        }


        if (dtoOld.getLast_name()!=null && dtoNew.getLast_name()!=null){
            if (dtoOld.getLast_name()!=dtoNew.getLast_name()){
                updateQuery.setField(RegisteredUser.lastname(),dtoNew.getLast_name());
            }
        }


        if (dtoOld.getPassword()!=null && dtoNew.getPassword()!=null){
            if (dtoOld.getPassword()!=dtoNew.getPassword()){
                updateQuery.setField(RegisteredUser.password(),dtoNew.getPassword());
            }
        }


        if (dtoOld.getType_id()!=0 && dtoNew.getType_id()!=0){
            if (dtoOld.getType_id()!=dtoNew.getType_id()){
                updateQuery.setField(RegisteredUser.type_id(),dtoNew.getType_id());
            }
        }


        if (dtoOld.getEmail()!=null && dtoNew.getEmail()!=null){
            if (dtoOld.getEmail()!=dtoNew.getEmail()){
                updateQuery.setField(RegisteredUser.email(),dtoNew.getEmail());
            }
        }

        updateQuery.firstCondition(RegisteredUser.id(),Operators.EQUAL,dtoOld.getUser_id());
        return update();

    }


    public int deleteByUserId(int userId){
        if (existsByUserId(userId)){
            prepareDelete();
            deleteQuery.firstCondition(RegisteredUser.id(),Operators.EQUAL,userId);
            return delete();
        }else{
            return 0;
        }
    }
    public int deleteByUserName(String userName){
        if (existsByUserName(userName)){
            prepareDelete();
            deleteQuery.firstCondition(RegisteredUser.username(),Operators.EQUAL,userName);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(RegisteredUserDTO dto) {
        return deleteByUserId(dto.getUser_id());
    }


}
