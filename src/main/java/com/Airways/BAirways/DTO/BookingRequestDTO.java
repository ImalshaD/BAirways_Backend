package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingRequestDTO {
    private int trip_id;
    private int seat_id;
    private List<PassengerDTO> list;
}
