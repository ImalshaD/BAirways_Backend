
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.PricesDTO;
import com.Airways.BAirways.Entity.Passenger;
import com.Airways.BAirways.Entity.Prices;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class PricesRepo extends Repo<PricesDTO> {



    public PricesRepo() {
        super(Prices.tablename());
    }

    public PricesDTO getByRouteIdClassId(int routeId,int classId){
        if (existsByRouteAndClass(routeId,classId)){
            prepare();
            selectQuery.firstCondition(Prices.routeid(),Operators.EQUAL,routeId);
            selectQuery.joinCondition(JoinOperators.AND,Prices.classid(),Operators.EQUAL,classId);
            PricesDTO pricesDTO = new PricesDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<PricesDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(pricesDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean existsByRouteAndClass(int routeID,int classID){
        prepare();
        selectQuery.firstCondition(Prices.routeid(), Operators.EQUAL,routeID);
        selectQuery.joinCondition(JoinOperators.AND,Prices.classid(),Operators.EQUAL,classID);
        return exists();
    }

    @Override
    public boolean existsByPrimaryKey(PricesDTO dto) {
        return existsByRouteAndClass(dto.getRoute_id(),dto.getClass_id());
    }

    @Override
    public int insertRecord(PricesDTO dto) {

        prepareInsert();
        insertQuery.addValue(Prices.routeid(),dto.getRoute_id());
        insertQuery.addValue(Prices.classid(),dto.getClass_id());
        insertQuery.addValue(Prices.price(),dto.getPrice());
        return insert();

    }

    @Override
    public int updateRecord(PricesDTO dtoOld, PricesDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getPrice()!=0 && dtoNew.getPrice()!=0){
            if (dtoOld.getPrice()!=dtoNew.getPrice()){
                updateQuery.setField(Prices.price(),dtoNew.getPrice());
            }
        }

        updateQuery.firstCondition(Prices.routeid(),Operators.EQUAL,dtoOld.getRoute_id());
        updateQuery.joinCondition(JoinOperators.AND,Prices.classid(),Operators.EQUAL,dtoOld.getClass_id());
        return update();

    }


    public int deleteByRouteIdClassId(int routeId,int classId){
        if (existsByRouteAndClass(routeId,classId)){
            prepareDelete();
            deleteQuery.firstCondition(Prices.routeid(),Operators.EQUAL,routeId);;
            deleteQuery.joinCondition(JoinOperators.AND,Prices.classid(),Operators.EQUAL,classId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(PricesDTO dto) {
        return deleteByRouteIdClassId(dto.getRoute_id(),dto.getClass_id());
    }


}
