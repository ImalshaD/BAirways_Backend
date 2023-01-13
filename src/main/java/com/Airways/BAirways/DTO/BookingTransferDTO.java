package com.Airways.BAirways.DTO;

import com.Airways.BAirways.Service.PassengerService;
import com.Airways.BAirways.Service.RouteService;
import com.Airways.BAirways.Service.TripService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingTransferDTO {
    private TripService tripService;
    private RouteService routeService;
    private PassengerService passengerService;
    private int booking_id;
    private String from_iata;
    private String to_iata;
    private LocalDate schedule;
    private LocalTime departure;
    private LocalTime arrival;

    private String fullName;
    private int status;

    private int flight_id;

    private int class_id;

    private int seat_id;
    public BookingTransferDTO(BookingDTO bookingDT,int status){
        booking_id=bookingDT.getBooking_id();
        tripService = new TripService();
        routeService = new RouteService();
        TripDTO tripDTO = tripService.getByTripID(bookingDT.getTrip_id());
        RouteDTO routeDTO = routeService.getByRouteID(tripDTO.getRoute_id());

        this.seat_id =bookingDT.getSeat_id();
        class_id = bookingDT.getClass_id();
        from_iata = routeDTO.getFrom_airport();
        to_iata = routeDTO.getTo_airport();
        schedule = tripDTO.getScheduled_date();
        departure = tripDTO.getDeparture();
        flight_id = tripDTO.getPlane_id();
        arrival = tripDTO.getArrival();
        if (bookingDT.getPassenger_id()!=0) {
            passengerService = new PassengerService();
            PassengerDTO passengerDTO = passengerService.getByID(bookingDT.getPassenger_id());
            fullName = passengerDTO.getFirst_name() + " " + passengerDTO.getLast_name();
        }

        this.status = status;
    }

}
