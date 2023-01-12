
package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirplaneModalDTO{
        private int modal_id;

    private String modal_name;

    private int manufacture_id;

    private int seating_capacity;

    private int seat_cols_firstclass;

    private int seat_rows_firstclass;

    private int seat_rows_economyclass;

    private int seat_cols_economyclass;

    private int seat_cols_businessclass;

    private int seat_rows_businessclass;
}
