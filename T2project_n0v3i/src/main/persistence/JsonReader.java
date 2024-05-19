package persistence;

import model.Flight;
import model.FlightLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// Modeled on JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlightLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlightLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private FlightLog parseFlightLog(JSONObject jsonObject) {
        // String name = jsonObject.getString("name"); // Use "name" instead of "brand"
        FlightLog i = new FlightLog();

        // Parse the "flights" array
        JSONArray flightsArray = jsonObject.getJSONArray("flights");
        for (Object json : flightsArray) {
            JSONObject flightJson = (JSONObject) json;
            String aircraftModel = flightJson.getString("aircraftModel");
            int flightHours = flightJson.getInt("flightHours");
            String date = flightJson.getString("date");
            String departureAirport = flightJson.getString("departureAirport");
            String arrivalAirport = flightJson.getString("arrivalAirport");

            Flight flight = new Flight(aircraftModel, flightHours, date, departureAirport, arrivalAirport);
            i.addFlight(flight);
        }

        return i;
    }
}
