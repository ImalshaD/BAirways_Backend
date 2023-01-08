package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO {
        private int booking_id;

    private int trip_id;

    private int passenger_id;

    private int seat_id;

    private int class_id;

    private double cost;

    private int status_id;
}
