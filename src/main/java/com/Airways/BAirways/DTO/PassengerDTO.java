package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerDTO {
        private int passenger_id;

    private String passport_number;

    private String nationality;

    private String first_name;

    private String last_name;

    private String email;

    private String contact_number;

    private LocalDate b_day;
    private int seat_id;
}
