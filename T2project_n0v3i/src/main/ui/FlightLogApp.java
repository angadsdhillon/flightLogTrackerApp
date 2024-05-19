package ui;

import model.Flight;
import model.FlightLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// App class which puts the app to the console.
public class FlightLogApp {
    private FlightLog flightLog;
    private Flight flight;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";


    // EFFECTS: Runs the flightLog application.  // NOTE TO SELF!: throws FileNotFoundException removed
    public FlightLogApp() {
        input = new Scanner(System.in);
        flightLog = new FlightLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFlightLog();
    }

    // MODIFIES: this
    // EFFECTS: Processes user input
    private void runFlightLog() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for using the Flight Log Tracker!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            addFlightToFlightLog();
        } else if (command.equals("2")) {
            viewFlightLog();
        } else if (command.equals("3")) {
            viewLongHaulFlights();
        } else if (command.equals("4")) {
            viewNumTotalFlights();
        } else if (command.equals("5")) {
            removeFlightFromFlightLog();
        } else if (command.equals("6")) {
            viewNumLongHaulFlights();
        } else if (command.equals("7")) {
            viewFlightDetails();
        } else if (command.equals("8")) {
            viewTotalFlightHours();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Invalid option. Please select a valid one.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes FlightLog and Flight.
    private void init() {
        flightLog = new FlightLog();
        flight = new Flight("Boeing 777", 9, "Dec 26, 2023",
                "YVR", "LHR");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to the user.
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1 -> Add a flight to the flight log");
        System.out.println("2 -> View the list of flights in flight log");
        System.out.println("3 -> View the list of long haul flights in flight log");
        System.out.println("4 -> View the number of flights in the flight log");
        System.out.println("5 -> Remove a flight from the flight log");
        System.out.println("6 -> View the number of long haul flights in flight log");
        System.out.println("7 -> View flight details");
        System.out.println("8 -> View total flight hours logged in flight log");
        System.out.println("s -> save flight log to file");
        System.out.println("l -> load flight log from file");
        System.out.println("q -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a flight to the flight log
    private void addFlightToFlightLog() {
        System.out.print("Enter aircraft model: ");
        String aircraftModel = input.next();
        System.out.print("Enter flight hours: ");
        int flightHours = input.nextInt();
        // System.out.print("Enter if flight isLongHaul: ");
        // boolean isLongHaul = input.nextBoolean();
        System.out.print("Enter date of departure: ");
        String date = input.next();
        System.out.print("Enter airport of departure: ");
        String departureAirport = input.next();
        System.out.print("Enter airport of arrival: ");
        String arrivalAirport = input.next();

        Flight flight = new Flight(aircraftModel, flightHours, date, departureAirport, arrivalAirport);
        flightLog.addFlight(flight);
        System.out.println("Flight added to flightLog.");
    }

    // MODIFIES: this, flightLog
    // EFFECTS: Views the list of flights in the flightLog.
    private void viewFlightLog() {
        System.out.println("Flights in flightLog:");
        for (Flight flight : flightLog.getFlightsLog()) {
            System.out.println(flight.getFlightDetails());
        }
    }

    // MODIFIES: this, flightLog
    // EFFECTS: Views the list of flights in the flightLog that are longHaul.
    private void viewLongHaulFlights() {
        System.out.println("Long haul flights in flightLog:");
        for (Flight flight : flightLog.getLongHaulFlights()) {
            System.out.println(flight.getFlightDetails());
        }
    }

    // MODIFIES: this
    // EFFECTS: Shows the number of flights in the flightLog.
    private void viewNumTotalFlights() {
        int totalFlightLogSize = flightLog.getTotalFlights();
        System.out.println("Total flights in flight log: " + totalFlightLogSize);
    }

    // MODIFIES: this, flightLog
    // EFFECTS: Removes a flight from the flightLog depending on user input.
    private void removeFlightFromFlightLog() {
        viewFlightLog();
        System.out.print("Enter the date of the flight to remove from flight log: ");
        String date = input.next();
        System.out.print("Enter the departure airport of the flight to remove from flight log: ");
        String departureAirport = input.next();

        for (Flight flight : flightLog.getFlightsLog()) {
            if (flight.getDate().equals(date) && flight.getDepartureAirport().equals(departureAirport)) {
                flightLog.removeFlight(flight);
                System.out.println("Flight removed from flight log.");
                return;
            }
        }

        System.out.println("Flight not found in flight log.");
    }

    // MODIFIES: this
    // EFFECTS: Shows the number of long haul flights in the flightLog.
    private void viewNumLongHaulFlights() {
        int numLongHaulFlights = flightLog.getTotalLongHaulFlights();
        System.out.println("Total long haul flights in flight log: " + numLongHaulFlights);
    }

    // MODIFIES: this
    // EFFECTS: Shows the details of a specific flight in the flight log.
    private void viewFlightDetails() {
        viewFlightLog();
        System.out.print("Enter the date of the flight to view details: ");
        String date = input.next();
        System.out.print("Enter the departure airport of the flight to view details: ");
        String departureAirport = input.next();

        for (Flight flight : flightLog.getFlightsLog()) {
            if (flight.getDate().equals(date) && flight.getDepartureAirport().equals(departureAirport)) {
                System.out.println(flight.getFlightDetails());
                return;
            }
        }

        System.out.println("Flight not found in flight log.");
    }

    // MODIFIES: this
    // EFFECTS: Shows the total number of flight hours logged in the flightLog.
    private void viewTotalFlightHours() {
        int totalHours = flightLog.getTotalFlightHours();
        System.out.println("Total number of flight hours logged in flight log: " + totalHours);
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();// same
            jsonWriter.write(flightLog);
            jsonWriter.close();
            System.out.println("Saved " + "flightLog" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            flightLog = jsonReader.read(); // flightLog
            System.out.println("Loaded " + "flightLog" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
