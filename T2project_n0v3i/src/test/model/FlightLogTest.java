package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightLogTest {
    private FlightLog flightLog;
    private Flight flight;
    private Flight flight2;

    @BeforeEach
    void runBefore() {
        flightLog = new FlightLog();
        flight = new Flight("Boeing 777", 9, "Dec 26, 2023",
                "YVR", "LHR");
        flight2 = new Flight("Boeing 737", 5, "Dec 29, 2023",
                "LHR", "ZUR");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, flightLog.getTotalFlights()); // 0

        flightLog.addFlight(flight);
        flightLog.addFlight(flight2);
        assertEquals(2, flightLog.getTotalFlights()); // 2
    }

    @Test
    public void testAddFlight() {
        flightLog.addFlight(flight);
        assertTrue(flightLog.getFlightsLog().contains(flight));
    }

    @Test
    public void testRemoveFlight() {
        flightLog.addFlight(flight);
        assertTrue(flightLog.getFlightsLog().contains(flight));
        assertEquals(1, flightLog.getTotalFlights());

        flightLog.removeFlight(flight);
        flightLog.removeFlight(flight2);
        assertFalse(flightLog.getFlightsLog().contains(flight));
        assertEquals(0, flightLog.getTotalFlights());

        flightLog.addFlight(flight);
        flightLog.addFlight(flight2);
        assertTrue(flightLog.getFlightsLog().contains(flight));
        assertTrue(flightLog.getFlightsLog().contains(flight2));

        flightLog.removeFlight(flight);
        assertFalse(flightLog.getFlightsLog().contains(flight));
        assertTrue(flightLog.getFlightsLog().contains(flight2));
        assertEquals(1, flightLog.getTotalFlights());
    }

    @Test
    public void testGetFlightLog() {
        flightLog.addFlight(flight);
        flightLog.addFlight(flight2);
        assertTrue(flightLog.getFlightsLog().contains(flight));
        assertTrue(flightLog.getFlightsLog().contains(flight2));
        assertEquals(2, flightLog.getFlightsLog().size());

        flightLog.removeFlight(flight2);
        assertTrue(flightLog.getFlightsLog().contains(flight));
        assertFalse(flightLog.getFlightsLog().contains(flight2));
        assertEquals(1, flightLog.getFlightsLog().size());
    }

    @Test
    public void testGetLongHaulFlights() {
        flightLog.addFlight(flight);
        flightLog.addFlight(flight2);

        assertTrue(flightLog.getLongHaulFlights().contains(flight));
        assertFalse(flightLog.getLongHaulFlights().contains(flight2));
        assertEquals(1, flightLog.getLongHaulFlights().size());

        flightLog.removeFlight(flight);
        assertFalse(flightLog.getLongHaulFlights().contains(flight));
        assertEquals(0, flightLog.getLongHaulFlights().size());
    }

    @Test
    public void testGetTotalFlights() {
        flightLog.addFlight(flight);
        flightLog.addFlight(flight2);
        assertEquals(2, flightLog.getTotalFlights());
    }

    @Test
    public void testGetTotalLongHaulFlights() {
        flightLog.addFlight(flight);
        flightLog.addFlight(flight2);
        assertEquals(1, flightLog.getTotalLongHaulFlights());
    }

    @Test
    public void testGetTotalFlightHours() {
        flightLog.addFlight(flight);
        assertEquals(9, flightLog.getTotalFlightHours());

        flightLog.addFlight(flight2);
        assertEquals(14, flightLog.getTotalFlightHours());

        flightLog.removeFlight(flight);
        assertEquals(5, flightLog.getTotalFlightHours());
    }
}