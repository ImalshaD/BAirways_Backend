
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.SeatDTO;
import com.Airways.BAirways.Entity.Seat;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class SeatRepo extends Repo<SeatDTO> {




    public SeatRepo() {
        super(Seat.tablename());
    }

    public SeatDTO getBySeatId(int seatId){
        if (existsBySeatId(seatId)){
            prepareGet();
            selectQuery.firstCondition(Seat.seatid(),Operators.EQUAL,seatId);
            SeatDTO seatDTO = new SeatDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<SeatDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(seatDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsBySeatId(int seatId){
        prepare();
        selectQuery.firstCondition(Seat.seatid(), Operators.EQUAL,seatId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(SeatDTO dto) {
        return existsBySeatId(dto.getSeat_id());
    }



    @Override
    public int insertRecord(SeatDTO dto) {

        prepareInsert();
        insertQuery.addValue(Seat.airplaneid(),dto.getAirplane_id());
        insertQuery.addValue(Seat.classid(),dto.getClass_id());
        insertQuery.addValue(Seat.colid(),dto.getCol_id());
        insertQuery.addValue(Seat.rowid(),dto.getRow_id());
        return insert();

    }

    @Override
    public int updateRecord(SeatDTO dtoOld, SeatDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getAirplane_id()!=0 && dtoNew.getAirplane_id()!=0){
            if (dtoOld.getAirplane_id()!=dtoNew.getAirplane_id()){
                updateQuery.setField(Seat.airplaneid(),dtoNew.getAirplane_id());
            }
        }


        if (dtoOld.getClass_id()!=0 && dtoNew.getClass_id()!=0){
            if (dtoOld.getClass_id()!=dtoNew.getClass_id()){
                updateQuery.setField(Seat.classid(),dtoNew.getClass_id());
            }
        }


        if (dtoOld.getCol_id()!=0 && dtoNew.getCol_id()!=0){
            if (dtoOld.getCol_id()!=dtoNew.getCol_id()){
                updateQuery.setField(Seat.colid(),dtoNew.getCol_id());
            }
        }


        if (dtoOld.getRow_id()!=0 && dtoNew.getRow_id()!=0){
            if (dtoOld.getRow_id()!=dtoNew.getRow_id()){
                updateQuery.setField(Seat.rowid(),dtoNew.getRow_id());
            }
        }

        updateQuery.firstCondition(Seat.seatid(),Operators.EQUAL,dtoOld.getSeat_id());
        return update();

    }


    public int deleteBySeatId(int seatId){
        if (existsBySeatId(seatId)){
            prepareDelete();
            deleteQuery.firstCondition(Seat.seatid(),Operators.EQUAL,seatId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(SeatDTO dto) {
        return deleteBySeatId(dto.getSeat_id());
    }


}
