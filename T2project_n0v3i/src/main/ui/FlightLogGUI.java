package ui;

import model.Event;
import model.EventLog;
import model.Flight;
import model.FlightLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// EFFECTS: Creates application's main window frame.
public class FlightLogGUI extends JFrame {
    private FlightLog flightLog;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/flightLog.json";

    private JButton addButton;
    private JButton viewButton;
    private JButton numFlightsButton;
    private JButton removeButton;
    private JButton viewNumLongHaulButton;
    private JButton viewDetailsButton;
    private JButton totalFlightHoursButton;
    private JButton saveButton;
    private JButton loadButton;

    private JTextArea flightLogTextArea;

    // MODIFIES: this
    // EFFECTS: Constructs a new FlightLogGUI with buttons and a display area for flights.
    //          Initializes the FlightLog and sets up the layout.
    //          Adds action listeners to the buttons and sets window properties.
    //          Adds the image to be displayed when app is turned off.
    public FlightLogGUI() {
        setTitle("Flight Log Application");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load the American flag image NEW!!!!!!!
        ImageIcon icon = new ImageIcon("./data/plane_landing.jpg");
        JLabel label = new JLabel(icon);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, label, "Thank you for using flight log manager",
                        JOptionPane.PLAIN_MESSAGE);
                displayLoggedEvents();
            }
        });

        flightLog = new FlightLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeComponents();
        addComponents();

        setVisible(true);
    }

    // EFFECTS: Initializes the FlightLog and sets the layout.
    private void initializeComponents() {
        addButton = new JButton("Add Flight");
        addButton.setBackground(new Color(255, 209, 220));

        viewButton = new JButton("View Flights");
        viewButton.setBackground(new Color(152, 251, 152));

        numFlightsButton = new JButton("View Total Flights");
        numFlightsButton.setBackground(new Color(173, 216, 230));

        removeButton = new JButton("Remove Flight");
        removeButton.setBackground(new Color(255, 218, 185));

        viewNumLongHaulButton = new JButton("View Long Haul Flights");
        viewNumLongHaulButton.setBackground(new Color(255, 255, 224));

        viewDetailsButton = new JButton("View Flight Details");
        viewDetailsButton.setBackground(new Color(175, 238, 238));

        totalFlightHoursButton = new JButton("View Total Flight Hours");
        totalFlightHoursButton.setBackground(new Color(230, 230, 250));

        saveButton = new JButton("Save Flight Log");
        saveButton.setBackground(new Color(123, 212, 190));

        loadButton = new JButton("Load Flight Log");
        loadButton.setBackground(new Color(187, 234, 221));

        flightLogTextArea = new JTextArea();
        flightLogTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(flightLogTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addActionListeners();
    }

    // EFFECTS: Adds components to the frame, including buttons.
    private void addComponents() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(numFlightsButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewNumLongHaulButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(totalFlightHoursButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        JScrollPane scrollPane = new JScrollPane(flightLogTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: Adds action listeners to the buttons for specific actions.
    private void addActionListeners() {
        addButton.addActionListener(e -> addFlightToFlightLog());
        viewButton.addActionListener(e -> viewFlightLog());
        numFlightsButton.addActionListener(e -> viewTotalFlights());
        removeButton.addActionListener(e -> removeFlight());
        viewNumLongHaulButton.addActionListener(e -> viewLongHaulFlights());
        viewDetailsButton.addActionListener(e -> viewFlightDetails());
        totalFlightHoursButton.addActionListener(e -> viewTotalFlightHours());
        saveButton.addActionListener(e -> saveFlightLog());
        loadButton.addActionListener(e -> loadFlightLog());
    }

    // MODIFIES: this, flightLog
    // EFFECTS: Adds a flight to the flightLog.
    private void addFlightToFlightLog() {
        String aircraftModel = JOptionPane.showInputDialog("Enter aircraft model:");
        int flightHours = Integer.parseInt(JOptionPane.showInputDialog("Enter flight hours:"));
        String date = JOptionPane.showInputDialog("Enter date of departure:");
        String departureAirport = JOptionPane.showInputDialog("Enter airport of departure:");
        String arrivalAirport = JOptionPane.showInputDialog("Enter airport of arrival:");

        Flight flight = new Flight(aircraftModel, flightHours, date, departureAirport, arrivalAirport);
        flightLog.addFlight(flight);
        JOptionPane.showMessageDialog(null, "Flight added to flight log.");
    }

    // EFFECTS: Displays the flight log in the text area.
    private void viewFlightLog() {
        flightLogTextArea.setText("");
        List<Flight> flights = flightLog.getFlightsLog();
        for (Flight flight : flights) {
            flightLogTextArea.append(flight.getFlightDetails() + "\n");
        }
    }

    // EFFECTS Displays the total number of flights in flightLog.
    private void viewTotalFlights() {
        int totalFlights = flightLog.getTotalFlights();
        JOptionPane.showMessageDialog(null, "Total flights in flight log: " + totalFlights);
    }

    // MODIFIES: this, flightLog
    // EFFECTS: Removes a flight from the flightLog.
    private void removeFlight() {
        String date = JOptionPane.showInputDialog("Enter the date of the flight to remove from flight log:");
        String departureAirport =
                JOptionPane.showInputDialog("Enter the departure airport of the flight to remove from flight log:");
        for (Flight flight : flightLog.getFlightsLog()) {
            if (flight.getDate().equals(date) && flight.getDepartureAirport().equals(departureAirport)) {
                flightLog.removeFlight(flight);
                JOptionPane.showMessageDialog(null, "Flight removed from flight log.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Flight not found in flight log.");
    }

    // EFFECTS: Displays the long haul flights in the flight log in the text area.
    private void viewLongHaulFlights() {
        flightLogTextArea.setText("");
        List<Flight> longHaulFlights = flightLog.getLongHaulFlights();
        for (Flight flight : longHaulFlights) {
            flightLogTextArea.append(flight.getFlightDetails() + "\n");
        }
    }

    // EFFECTS: Displays details about a specific flight in flight log.
    private void viewFlightDetails() {
        String date = JOptionPane.showInputDialog("Enter the date of the flight to view details:");
        String departureAirport =
                JOptionPane.showInputDialog("Enter the departure airport of the flight to view details:");
        for (Flight flight : flightLog.getFlightsLog()) {
            if (flight.getDate().equals(date) && flight.getDepartureAirport().equals(departureAirport)) {
                JOptionPane.showMessageDialog(null, flight.getFlightDetails());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Flight not found in flight log.");
    }

    // EFFECTS: Displays total number of flight hours logged in flight log.
    private void viewTotalFlightHours() {
        int totalFlightHours = flightLog.getTotalFlightHours();
        JOptionPane.showMessageDialog(null, "Total number of flight hours logged in flight log: "
                + totalFlightHours);
    }

    // EFFECTS: Saves the flightLog to a file.
    private void saveFlightLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(flightLog);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Flight log saved to file.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: Loads the flightLog from a file.
    private void loadFlightLog() {
        try {
            flightLog = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Flight log loaded from file.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Loads the image from a file and creates ImageIcon from the loaded image.
    private ImageIcon loadIcon(String imagePath) {
        // Load the image
        Image image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create an ImageIcon from the loaded image
        return new ImageIcon(image);
    }

    // EFFECTS: Runs the FlightLogGUI.
    public static void main(String[] args) {
        new FlightLogGUI();
    }

    // EFFECTS: Displays the logged events.
    private static void displayLoggedEvents() {
        System.out.println("Events logged since the Flight Log application started:");

        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }
}
