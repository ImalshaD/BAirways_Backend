
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.Booking_logDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.Booking_log;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class Booking_logRepo extends Repo<Booking_logDTO> {



    public Booking_logRepo() {
        super(Booking_log.tablename());
    }

    public Booking_logDTO getByLogNum(int logNum){
        if (existsByLogNum(logNum)){
            prepareGet();
            selectQuery.firstCondition(Booking_log.lognum(),Operators.EQUAL,logNum);
            Booking_logDTO booking_logDTO = new Booking_logDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<Booking_logDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(booking_logDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean existsByLogNum(int logNum){
        prepare();
        selectQuery.firstCondition(Booking_log.lognum(), Operators.EQUAL,logNum);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(Booking_logDTO dto) {
        return existsByLogNum(dto.getLog_num());
    }



    @Override
    public int insertRecord(Booking_logDTO dto) {

        prepareInsert();

        insertQuery.addValue(Booking_log.userid(),dto.getUser_id());
        insertQuery.addValue(Booking_log.bookingid(),dto.getBooking_id());
        return insert();

    }

    @Override
    public int updateRecord(Booking_logDTO dtoOld, Booking_logDTO dtoNew) {

        prepareInsert();

        if (dtoOld.getUser_id()!=0 && dtoNew.getUser_id()!=0){
            if (dtoOld.getUser_id()!=dtoNew.getUser_id()){
                updateQuery.setField(Booking_log.userid(),dtoNew.getUser_id());
            }
        }


        if (dtoOld.getBooking_id()!=0 && dtoNew.getBooking_id()!=0){
            if (dtoOld.getBooking_id()!=dtoNew.getBooking_id()){
                updateQuery.setField(Booking_log.bookingid(),dtoNew.getBooking_id());
            }
        }

        updateQuery.firstCondition(Booking_log.lognum(),Operators.EQUAL,dtoOld.getLog_num());
        return update();

    }


    public int deleteByLogNum(int logNum){
        if (existsByLogNum(logNum)){
            prepareDelete();
            deleteQuery.firstCondition(Booking_log.lognum(),Operators.EQUAL,logNum);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(Booking_logDTO dto) {
        return deleteByLogNum(dto.getLog_num());
    }


}
