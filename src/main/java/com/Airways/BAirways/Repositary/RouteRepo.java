
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.RouteDTO;
import com.Airways.BAirways.Entity.Route;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class RouteRepo extends Repo<RouteDTO> {



    public RouteRepo() {
        super(Route.tablename());
    }

    public RouteDTO getByRouteId(int routeId){
        if (existsByRouteId(routeId)){
            prepare();
            selectQuery.firstCondition(Route.routeid(),Operators.EQUAL,routeId);
            RouteDTO routeDTO = new RouteDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<RouteDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(routeDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByRouteId(int routeId){
        prepare();
        selectQuery.firstCondition(Route.routeid(), Operators.EQUAL,routeId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(RouteDTO dto) {
        return existsByRouteId(dto.getRoute_id());
    }



    @Override
    public int insertRecord(RouteDTO dto) {

        prepareInsert();
        insertQuery.addValue(Route.from(),dto.getFrom_airport());
        insertQuery.addValue(Route.to(),dto.getTo_airport());
        return insert();

    }

    @Override
    public int updateRecord(RouteDTO dtoOld, RouteDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getFrom_airport()!=null && dtoNew.getFrom_airport()!=null){
            if (dtoOld.getFrom_airport()!=dtoNew.getFrom_airport()){
                updateQuery.setField(Route.from(),dtoNew.getFrom_airport());
            }
        }


        if (dtoOld.getTo_airport()!=null && dtoNew.getTo_airport()!=null){
            if (dtoOld.getTo_airport()!=dtoNew.getTo_airport()){
                updateQuery.setField(Route.to(),dtoNew.getTo_airport());
            }
        }

        updateQuery.firstCondition(Route.routeid(),Operators.EQUAL,dtoOld.getRoute_id());
        return update();

    }


    public int deleteByRouteId(int routeId){
        if (existsByRouteId(routeId)){
            prepareDelete();
            deleteQuery.firstCondition(Route.routeid(),Operators.EQUAL,routeId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(RouteDTO dto) {
        return deleteByRouteId(dto.getRoute_id());
    }


}
