package persistence;

import model.Flight;
import model.FlightLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            FlightLog i = new FlightLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            FlightLog i = new FlightLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(i);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            i = reader.read();
            // assertEquals("My work room", i.getName());
            assertEquals(0, i.getTotalFlights());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            FlightLog i = new FlightLog();
            i.addFlight(new Flight("Boeing 777", 13, "Jan 1, 2020", "SEA",
                    "DXB"));
            i.addFlight(new Flight("Airbus A320", 3, "Dec 31, 2020", "LHR",
                    "ZUR"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(i);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            i = reader.read();
            // assertEquals("My work room", i.getName());
            List<Flight> flights = i.getFlightsLog();
            assertEquals(2, flights.size());
            checkFlight("Boeing 777", 13, "Jan 1, 2020", "SEA", "DXB",
                    flights.get(0));
            checkFlight("Airbus A320", 3, "Dec 31, 2020", "LHR", "ZUR",
                    flights.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
