# My Personal Project

## Flight Log Manager


**Flight Log Tracker:**

This program will allow pilots to view the flights that they have 
flown by viewing their flight log in which they can add/remove flights
as they are flown or cancelled. This app will allow its users to fill out
information about the flight that they have flown and add it into their
flight log. This app will allow pilots to also enter details about their 
flight as they add it into the flight log, such as flight hours, aircraft 
model, departure airport/arrival airport, along with some other important
flight details. This app will list all the flights that the pilot has flown 
along with the important details of each flight. There will be a list of 
trips flown by the pilot which will consist of an arbitrary number of flights. 

This application will be used by pilots to manage and track their flight logs, 
which are an essential part of a pilots career, as it is used to track the
number of flight hours a pilot has acquired, which determines their employability 
and promotions. Flight logs are also vital for safety, as it documents key details of each 
flight, which can be tracked and viewed should any problems arise in the future. 
I am an aviation geek, and have loved airplanes for as long as I can remember, and 
I am very knowledgeable in almost every aspect of aviation, especially commercial 
aviation. I also understand the difficulties of pilots' lives and realize that all 
the paperwork and documentation can get very tiring before and after each flight. 
Therefore, I decided to create an app that will help manage and track flight logs, 
and present it to users in a simple manner. This project takes my lifelong passion
I've had for airplanes and combines it with the desire to make tedious tasks, that 
are to be performed daily, simpler and more enjoyable. 

## User Stories

- As a user, I want to be able to add a flight to my flight log
- As a user, I want to be able to view the list of flights in my flight log
- As a user, I want to be able to mark a flight as long haul or short haul
- As a user, I want to be able to see the number of flights in my flight log
  and the number of flights that are long haul
- As a user, I want to be able to remove a flight from my flight log
- As a user, I want to be able to add a flight to my flight log, and specify its
  flight hours, aircraft model, departure date, and departure/arrival airports
- As a user, I want to be able to view flight details of the flights added to 
  my flight log
- As a user, I want to be able to view my total flight hours logged.           


**Phase 2 Newly Added User Stories:**

- As a user, I want to be able to save my flight log list to file if I choose
  to do so

- As a user, I want to be able to be able to load my flight log from file
  if I choose to do so


# Instructions to Run the App

- First run the app via the FlightLogGUI class, then you will see a panel with
  buttons.

- You can generate the first required action related to the user story "adding
  multiple Xs to a Y" by clicking the "Add Flight" button on the panel, and then it will ask
  you to type in information about the flight you wish to add, and once all the prompts
  are filled out, the flight will be added to the flight log.

- You can generate the second required action related to the user story "adding
  multiple Xs to a Y" by clicking the "View Flights" button. This will show a list of
  all the flights which were added to the flight log on the panel.

- You can generate the third required action related to the user story "adding
  multiple Xs to a Y" by clicking the "Remove Flight" button on the panel, and then it will ask
  you to type in information about the flight you wish to remove, and once all the prompts are
  filled out, the flight will be removed from the flight log. 

- You can generate the fourth required action related to the user story "adding
  multiple Xs to a Y" by clicking the "View Flight Details" button. This will ask you to 
  type in information about the flight you wish to view details about, and once all the
  prompts are filled out, the flight details will be shown. 

- You can generate the fifth required action related to the user story "adding
  multiple Xs to a Y" by clicking the "View Long Haul Flights" button, which will display
  a subset of the flight log which only contains flights that are long haul. 

- You can generate the sixth required action related to the user story "adding
  multiple Xs to a Y" by clicking the "View Total Flights" button, which will show the
  total number of flights that have been added to the flight log. 

- You can generate the seventh required action related to the user story "adding
  multiple Xs to a Y" by clicking the "View Total Flight Hours" button, which will show
  the total number of flight hours logged in the flight log. 

- You can locate my visual component by pressing "x" in the top right corner of the
  GUI panel to exit the app or "ok" at the bottom center of the panel, which will display a 
  "thank you" message along with an image of an airplane.

- You can save the state of my application by clicking the "save" button in the GUI
  panel.

- You can reload the state of my application by clicking the "load" button in the
  GUI panel.


# Phase 4: Task 2

- Events logged since the Flight Log application started:
- Sat Apr 06 19:55:25 PDT 2024
- Flight added to flight log.
- Sat Apr 06 19:55:28 PDT 2024
- Flight log viewed.
- Sat Apr 06 19:55:38 PDT 2024
- Total number of flights viewed.
- Sat Apr 06 19:55:42 PDT 2024
- Long haul flights viewed.
- Sat Apr 06 19:55:44 PDT 2024
- Total number of flight hours viewed.
- Sat Apr 06 19:55:50 PDT 2024
- Flight log viewed.
- Sat Apr 06 19:56:36 PDT 2024
- Flight removed from flight log.
- Sat Apr 06 19:56:39 PDT 2024
- Flight log viewed.


# Phase 4: Task 3

In order to refactor this project, I could apply the Observer pattern to enhance the design of the FlightLogApp project. 
The FlightLog class would be extending the FlightLogSubject (new class to be added), which includes methods to
manage observers. The FlightLogObserver interface will be introduced to define the method that observers should implement,
such as the update method. The Flight class will then be a concrete observer, implementing
the FlightLogObserver interface, and using the update method to change things within itself whenever a flight is added
to the flight log. 

In this setup, the FlightLog class acts as the subject, responsible for managing the state (the list of flights) and
notifying its observers of any changes. The Flight class, on the other hand, acts as the observer, specifying
how it should respond when a new flight is added to the inventory. This separation of concerns allows for a more
modular and extensible design. New observers can be added without modifying the FlightLog class, increasing flexibility
and reducing code dependencies. If in the future, the Flight class must add on additional functionality, which would
likely need to update whenever it is added into the flight log, it can easily be achieved without extensive modification
to the code through the observer pattern. 

