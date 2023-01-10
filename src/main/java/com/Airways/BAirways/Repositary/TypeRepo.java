
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.TypeDTO;
import com.Airways.BAirways.Entity.Type;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class TypeRepo extends Repo<TypeDTO> {



    public TypeRepo() {
        super(Type.tablename());
    }

    public TypeDTO getByTypeId(int typeId){
        if (existsByTypeId(typeId)){
            prepareGet();
            selectQuery.firstCondition(Type.typeid(),Operators.EQUAL,typeId);
            TypeDTO typeDTO = new TypeDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<TypeDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(typeDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public TypeDTO getByTypeName(String name){
        if (existsByTypeName(name)){
            prepareGet();
            selectQuery.firstCondition(Type.name(),Operators.EQUAL,name);
            TypeDTO typeDTO = new TypeDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<TypeDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(typeDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByTypeId(int typeId){
        prepare();
        selectQuery.firstCondition(Type.typeid(), Operators.EQUAL,typeId);
        return exists();
    }
    public boolean existsByTypeName(String name){
        prepare();
        selectQuery.firstCondition(Type.name(), Operators.EQUAL,name);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(TypeDTO dto) {
        return existsByTypeId(dto.getType_id());
    }



    @Override
    public int insertRecord(TypeDTO dto) {

        prepareInsert();
        insertQuery.addValue(Type.name(),dto.getName());
        insertQuery.addValue(Type.discount(),dto.getDiscount());
        return insert();

    }

    @Override
    public int updateRecord(TypeDTO dtoOld, TypeDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getName()!=null && dtoNew.getName()!=null){
            if (dtoOld.getName()!=dtoNew.getName()){
                updateQuery.setField(Type.name(),dtoNew.getName());
            }
        }
        if (dtoOld.getDiscount()!=0 && dtoNew.getDiscount()!=0){
            if (dtoOld.getDiscount()!=dtoNew.getDiscount()){
                updateQuery.setField(Type.discount(),dtoNew.getDiscount());
            }
        }

        updateQuery.firstCondition(Type.typeid(),Operators.EQUAL,dtoOld.getType_id());
        return update();

    }


    public int deleteByTypeId(int typeId){
        if (existsByTypeId(typeId)){
            prepareDelete();
            deleteQuery.firstCondition(Type.typeid(),Operators.EQUAL,typeId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(TypeDTO dto) {
        return deleteByTypeId(dto.getType_id());
    }


}
