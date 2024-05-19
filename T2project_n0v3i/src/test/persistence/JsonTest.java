package persistence;

import model.Flight;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFlight(String aircraftModel, int flightHours, String date, String departureAirport,
                                String arrivalAirport, Flight flight) {
        assertEquals(aircraftModel, flight.getAircraftModel());
        assertEquals(flightHours, flight.getFlightHours());
        assertEquals(date, flight.getDate());
        assertEquals(departureAirport, flight.getDepartureAirport());
        assertEquals(arrivalAirport, flight.getArrivalAirport());
        // assertEquals(isLongHaul, flight.isLongHaul());
    }
}