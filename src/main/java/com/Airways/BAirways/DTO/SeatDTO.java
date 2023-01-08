package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDTO {
        private int seat_id;

    private int airplane_id;

    private int class_id;

    private int col_id;

    private int row_id;
}
