package com.Airways.BAirways.DTO;

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
    private int seat_cols;
    private int seat_rows;
}
