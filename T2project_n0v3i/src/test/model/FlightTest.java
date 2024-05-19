package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {
    private Flight flight;
    private Flight shortFlight;
    private Flight flight2;

    @BeforeEach
    void runBefore() {
        flight = new Flight("Boeing 777", 9, "Dec 26, 2023",
                "YVR", "LHR");
        shortFlight = new Flight("Boeing 737", 5, "Dec 29, 2023",
                "LHR", "ZUR");
        flight2 = new Flight("Airbus A321", 6, "Dec 31, 2023",
                "YVR", "JFK");
    }

    @Test
    public void testConstructor() {
        assertEquals("Boeing 777", flight.getAircraftModel());
        assertEquals(9, flight.getFlightHours());
        assertTrue(flight.isLongHaul());
        assertEquals("Dec 26, 2023", flight.getDate());
        assertEquals("YVR", flight.getDepartureAirport());
        assertEquals("LHR", flight.getArrivalAirport());
    }

    @Test
    public void testGetAircraftModel() {
        assertEquals("Boeing 777", flight.getAircraftModel());
    }

    @Test
    public void testGetFlightHours() {
        assertEquals(9, flight.getFlightHours());
    }

    @Test
    public void testIsLongHaul() {
        assertTrue(flight.isLongHaul());
        assertFalse(flight2.isLongHaul());
        assertFalse(shortFlight.isLongHaul());
    }

    @Test
    public void testGetDate() {
        assertEquals("Dec 26, 2023", flight.getDate());
    }

    @Test
    public void testGetDepartureAirport() {
        assertEquals("YVR", flight.getDepartureAirport());
    }

    @Test
    public void testGetArrivalAirport() {
        assertEquals("LHR", flight.getArrivalAirport());
    }

    @Test
    public void testGetFlightDetails() {
        String expectedDetails = "aircraftModel: Boeing 777, flightHours: 9, isLongHaul: Yes, date: Dec 26, 2023, " +
                "departureAirport: YVR, arrivalAirport: LHR";
        assertEquals(expectedDetails, flight.getFlightDetails());

        String expectedInfo2 = "aircraftModel: Boeing 737, flightHours: 5, isLongHaul: No, date: Dec 29, 2023, " +
                "departureAirport: LHR, arrivalAirport: ZUR";
        assertEquals(expectedInfo2, shortFlight.getFlightDetails());
    }
}
