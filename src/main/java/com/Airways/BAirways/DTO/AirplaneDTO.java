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
    private int modal_id;
    private int manufactured_year;
    private String manufactured_country;
}
