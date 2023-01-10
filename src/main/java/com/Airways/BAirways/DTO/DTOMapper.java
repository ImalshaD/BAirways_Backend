package com.Airways.BAirways.DTO;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class DTOMapper<T> {
    public T maptoDTO(T dto, Map<String,Object> map) throws NoSuchFieldException, IllegalAccessException {

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field field = dto.getClass().getDeclaredField(entry.getKey());
            field.setAccessible(true);
            if ((field.getType()==int.class || field.getType()==long.class) && entry.getValue()==null) {
                field.set(dto, 0);
            } else if (field.getType()== LocalDate.class) {
                java.sql.Date x = (java.sql.Date) entry.getValue();
                field.set(dto, x.toLocalDate());
            }else if(field.getType() == LocalTime.class){
                java.sql.Time x =(java.sql.Time) entry.getValue();
                field.set(dto,x.toLocalTime());
            }else if(field.getType() == double.class){
                java.math.BigDecimal x =(java.math.BigDecimal) entry.getValue();
                field.set(dto,x.doubleValue());
            }else {
                field.set(dto, entry.getValue());
            }
        }
        return dto;
    }
}
