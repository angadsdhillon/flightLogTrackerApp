package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a log of flights.
public class FlightLog {
    private List<Flight> flights;

    // EFFECTS: Constructs a new flightLog that is empty.
    public FlightLog() {
        flights = new ArrayList<>();
    }

    // REQUIRES: The flight is not already contained in the flightLog.
    // MODIFIES: this
    // EFFECTS: Adds the provided flight to the end of the flightLog.
    public void addFlight(Flight flight) {
        flights.add(flight);
        EventLog.getInstance().logEvent(new Event("Flight added to flight log."));
    }

    // REQUIRES: The Flight is present in the flightLog.
    // MODIFIES: this
    // EFFECTS: Removes the provided flight from the flightLog.
    public void removeFlight(Flight flight) {
        flights.remove(flight);
        EventLog.getInstance().logEvent(new Event("Flight removed from flight log."));
    }

    // EFFECTS: Returns a list of flights in the flightLog.
    public List<Flight> getFlightsLog() {
        EventLog.getInstance().logEvent(new Event("Flight log viewed."));
        return flights;
    }

    // EFFECTS: Returns a list of long haul flights in the flightLog.
    public List<Flight> getLongHaulFlights() {
        List<Flight> longHaulFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (flight.isLongHaul()) {
                longHaulFlights.add(flight);
            }
        }
        EventLog.getInstance().logEvent(new Event("Long haul flights viewed."));
        return longHaulFlights;
    }

    // EFFECTS: Returns the total number of flights in the flightLog.
    public int getTotalFlights() {
        EventLog.getInstance().logEvent(new Event("Total number of flights viewed."));
        return flights.size();
    }

    // EFFECTS: Returns the number of flights that are longHaul.
    public int getTotalLongHaulFlights() {
        List<Flight> longHaulFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (flight.isLongHaul()) {
                longHaulFlights.add(flight);
            }
        }
        EventLog.getInstance().logEvent(new Event("Total number of log haul flights viewed."));
        return longHaulFlights.size();
    }

    // EFFECTS: Returns total number of flight hours logged in flightLog.
    public int getTotalFlightHours() {
        int totalHours = 0;

        for (Flight flight : flights) {
            totalHours = flight.getFlightHours() + totalHours;
        }
        EventLog.getInstance().logEvent(new Event("Total number of flight hours viewed."));
        return totalHours;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("flights", flights);

        JSONArray flightsArray = new JSONArray();
        for (Flight flight : flights) {
            flightsArray.put(flight.toJson());
        }

        json.put("flights", flightsArray);
        return json;
    }
}
