package com.Airways.BAirways.DTO;

import com.Airways.BAirways.Utility.Annotations.MyColoumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirplaneDTO {

    private int plane_id;
    private String modal;
    private int manufactured_year;
    private String manufactured_country;
    private int seating_capacity;
    private int seat_cols_firstclass;
    private int seat_rows_firstclass;
    private int seat_rows_economyclass;
    private int seat_cols_economyclass;
    private int seat_cols_businessclass;
    private int seat_rows_businessclass;
}
