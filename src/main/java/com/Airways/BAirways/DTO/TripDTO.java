package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripDTO {
        private int trip_id;
    private LocalDate scheduled_date;
    private LocalTime departure;

    private LocalTime arrival;

    private int route_id;

    private int plane_id;
    private int status_id;

}
