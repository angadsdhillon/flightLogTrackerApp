package persistence;

import model.Flight;
import model.FlightLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlightLog i = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            FlightLog i = reader.read();
            // assertEquals("My work room", i.getName());
            assertEquals(0, i.getTotalFlights());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            FlightLog i = reader.read();
            // assertEquals("My work room", i.getName());
            List<Flight> flights = i.getFlightsLog();
            assertEquals(2, flights.size());
            checkFlight("Boeing 777", 13, "Jan 1, 2020", "SEA", "DXB",
                    flights.get(0));
            checkFlight("Airbus A320", 3, "Dec 31, 2020", "LHR", "ZUR",
                    flights.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
