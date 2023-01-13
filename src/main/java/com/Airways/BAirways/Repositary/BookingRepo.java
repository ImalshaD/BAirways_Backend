
package com.Airways.BAirways.Repositary;

import com.Airways.BAirways.DTO.BookingDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.Booking;
import com.Airways.BAirways.Utility.QueryHelper.Operators.JoinOperators;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;

import java.util.List;
import java.util.Map;


public class BookingRepo extends Repo<BookingDTO> {



    public BookingRepo() {
        super(Booking.tablename());
    }

    public BookingDTO getByBookingId(int bookingId){
        if (existsByBookingId(bookingId)){
            prepareGet();
            selectQuery.firstCondition(Booking.bookingid(),Operators.EQUAL,bookingId);
            BookingDTO bookingDTO = new BookingDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<BookingDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(bookingDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByBookingId(int bookingId){
        prepare();
        selectQuery.firstCondition(Booking.bookingid(), Operators.EQUAL,bookingId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(BookingDTO dto) {
        return existsByBookingId(dto.getBooking_id());
    }



    @Override
    public int insertRecord(BookingDTO dto) {

        prepareInsert();

        insertQuery.addValue(Booking.tripid(),dto.getTrip_id());
        insertQuery.addValue(Booking.passengerid(),dto.getPassenger_id());
        insertQuery.addValue(Booking.seatid(),dto.getSeat_id());
        insertQuery.addValue(Booking.classid(),dto.getClass_id());
        insertQuery.addValue(Booking.cost(),dto.getCost());
        return insert();

    }

    @Override
    public int updateRecord(BookingDTO dtoOld, BookingDTO dtoNew) {

        prepareInsert();
        if (dtoOld.getTrip_id()!=0 && dtoNew.getTrip_id()!=0){
            if (dtoOld.getTrip_id()!=dtoNew.getTrip_id()){
                updateQuery.setField(Booking.tripid(),dtoNew.getTrip_id());
            }
        }


        if (dtoOld.getPassenger_id()!=0 && dtoNew.getPassenger_id()!=0){
            if (dtoOld.getPassenger_id()!=dtoNew.getPassenger_id()){
                updateQuery.setField(Booking.passengerid(),dtoNew.getPassenger_id());
            }
        }


        if (dtoOld.getSeat_id()!=0 && dtoNew.getSeat_id()!=0){
            if (dtoOld.getSeat_id()!=dtoNew.getSeat_id()){
                updateQuery.setField(Booking.seatid(),dtoNew.getSeat_id());
            }
        }


        if (dtoOld.getClass_id()!=0 && dtoNew.getClass_id()!=0){
            if (dtoOld.getClass_id()!=dtoNew.getClass_id()){
                updateQuery.setField(Booking.classid(),dtoNew.getClass_id());
            }
        }


        if (dtoOld.getCost()!=0 && dtoNew.getCost()!=0){
            if (dtoOld.getCost()!=dtoNew.getCost()){
                updateQuery.setField(Booking.cost(),dtoNew.getCost());
            }
        }
        updateQuery.firstCondition(Booking.bookingid(),Operators.EQUAL,dtoOld.getBooking_id());
        return update();

    }


    public int deleteByBookingId(int bookingId){
        if (existsByBookingId(bookingId)){
            prepareDelete();
            deleteQuery.firstCondition(Booking.bookingid(),Operators.EQUAL,bookingId);
            return delete();
        }else{
            return 0;
        }
    }
    @Override
    public int deleteRecord(BookingDTO dto) {
        return deleteByBookingId(dto.getBooking_id());
    }


}
