package model;

import org.json.JSONObject;

// Represents a flight with specified details.
public class Flight {
    private String aircraftModel;
    private int flightHours;
    private boolean isLongHaul;
    private String date;
    private String departureAirport;
    private String arrivalAirport;

    // EFFECTS: Constructs a new FlightLog with the provided aircraftModel, flightHours,
    //          date, departureAirport, and arrivalAirport.
    public Flight(String aircraftModel, int flightHours, String date, String departureAirport, String arrivalAirport) {
        this.aircraftModel = aircraftModel;
        this.flightHours = flightHours;
        // this.isLongHaul = false;
        this.date = date;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    // EFFECTS: Returns the aircraft model of the plane flown.
    public String getAircraftModel() {
        return aircraftModel;
    }

    // EFFECTS: Returns the number of flight hours of the flight.
    public int getFlightHours() {
        return flightHours;
    }

    // EFFECTS: Returns true if flight is longHaul (over 6 hours), false otherwise.
    public boolean isLongHaul() {
        if (this.flightHours > 6) {
            this.isLongHaul = true;
        }
        return isLongHaul;
    }

    // EFFECTS: Returns the departure date of the flight.
    public String getDate() {
        return date;
    }

    // EFFECTS: Returns the departure airport.
    public String getDepartureAirport() {
        return departureAirport;
    }

    // EFFECTS: Returns the arrival airport.
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    // EFFECTS: Returns a string containing information about the flight, including brand, model, seats, category,
    //          and availability.
    public String getFlightDetails() {
        return "aircraftModel: " + aircraftModel + ", flightHours: " + flightHours + ", isLongHaul: "
                + (isLongHaul() ? "Yes" : "No") + ", date: " + date + ", departureAirport: " + departureAirport
                + ", arrivalAirport: " + arrivalAirport;
    }

    // EFFECTS: Returns json which includes all the flight class's fields.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("aircraftModel", aircraftModel);
        json.put("flightHours", flightHours);
        json.put("date", date);
        json.put("departureAirport", departureAirport);
        json.put("arrivalAirport", arrivalAirport);
        json.put("isLongHaul", isLongHaul);
        return json;
    }
}
