package sharedRegions;

import java.util.Arrays;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import entities.BusDriverStates;
import entities.PassengerStates;
import entities.PorterStates;
import mainProject.SimulPar;

public class RepositoryInfo {

    /**** Report Values ****/
    static int passengersFinalDestination = 0;
    static int passengersInTransit = 0;
    static int bagsTransportedInThePlaneHold = 0;
    static int bagsLost = 0;

    /**** Flight ****/
    int flightNumber;                   //Number of the flight
    int passengersCount;                //Number of Passengers that have landed

    /**** File ****/
    private File f;
    private PrintWriter pw;

    /**** Entities Information ****/
    PorterStates porterState;           //State of the Porter
    BusDriverStates busDriverState;     //State of the Bus Driver
    PassengerStates[] passengerStates;  //State of the Passenger

    /**** Memory Regions ****/

    /**
     * Arrival Lounge
     */
    int luggageInPlaneHold;             //Number of Luggages in the plain hold
    char passengersSituation[];         //Passengers situation
    int passengersLuggage[];            //Luggage that the passengers brought in the begging of the journey
    int passengersLuggageCollected[];   //Luggage that was collected by the passengers

    /**
     * BaggageCollectionPoint
     */
    int luggageInConveyorBelt;          //Number of Luggages in the conveyor belt

    /**
     * TemporaryStorageArea
     */
    int luggageInStoreRoom;             //Number of Luggages in the store room

    /**
     * ArrivalTerminalTransferQuay
     */
    int busWaitingQueue[];              //Passengers Waiting Queue
    int busSeats[];                     //Passengers Seated on the Bus

    /**
     * General Repository Of Information instantiation
     *
     * Saves the loggs in a file
     */
    public RepositoryInfo() throws FileNotFoundException {
        f = new File(SimulPar.FILENAME);
        pw = new PrintWriter(f);

        String header = headerState();
        header = header.concat("\n");
        System.out.println(header);
        pw.write(header);
    }

    /**
     * Initialization of the Repository
     */
    public synchronized void init_repository(int flightNumber) {
        this.flightNumber = flightNumber;

        this.passengersCount = 0;
        this.luggageInPlaneHold = 0;
        this.luggageInStoreRoom = 0;
        this.luggageInConveyorBelt = 0;

        this.porterState = PorterStates.WAITING_FOR_A_PLANE_TO_LAND;
        this.busDriverState = BusDriverStates.PARKING_AT_THE_ARRIVAL_TERMINAL;
        this.passengerStates = new PassengerStates[SimulPar.PASSENGERS];
        for (int i = 0; i < SimulPar.PASSENGERS; i++) {
            this.passengerStates[i] = PassengerStates.WHAT_SHOULD_I_DO;
        }
        this.busWaitingQueue = new int[SimulPar.PASSENGERS];
        Arrays.fill(this.busWaitingQueue, -1);
        this.busSeats = new int[SimulPar.BUS_CAPACITY];
        Arrays.fill(this.busSeats, -1);
        this.passengersSituation = new char[SimulPar.PASSENGERS];
        Arrays.fill(this.passengersSituation, '-');
        passengersLuggage = new int [SimulPar.PASSENGERS];
        Arrays.fill(this.passengersLuggage, -1);
        passengersLuggageCollected = new int [SimulPar.PASSENGERS];
        Arrays.fill(this.passengersLuggageCollected, -1);
    }

    /***** GETTERS AND SETTERS ****/

    /**
     * Set Passenger State
     *
     * @param id int
     * @param state PassengerStates
     *
     */
    public synchronized void setPassengerState(int id, PassengerStates state ) {
        this.passengerStates[id] = state;
        export();
    }

    /**
     * Set Bus Driver State
     *
     * @param state PassengerStates
     *
     */
    public synchronized void setBusDriverState(BusDriverStates state ) {
        this.busDriverState = state;
        export();
    }

    /**
     * Set Porter State
     *
     * @param state PassengerStates
     *
     */
    public synchronized void setPorterState(PorterStates state ) {
        this.porterState = state;
        export();
    }

    /***** ACTIONS *******/

    /**
     * A flight has landed
     *
     * @param luggageInPlaneHold int
     *
     */
    public synchronized void flightLanded(int luggageInPlaneHold) {
        this.luggageInPlaneHold = luggageInPlaneHold;
        bagsTransportedInThePlaneHold += luggageInPlaneHold;
        export();
    }

    /**
     * A Passenger has arrived
     *
     * @param id int
     * @param isFinalDestination boolean
     * @param numberOfLuggages int
     *
     */
    public synchronized char passengerArrived(int id, boolean isFinalDestination, int numberOfLuggages) {
        char action;
        this.passengersCount++;
        this.passengersLuggage[id] = numberOfLuggages;
        this.passengerStates[id] = PassengerStates.WHAT_SHOULD_I_DO;
        if (isFinalDestination) {
            this.passengersSituation[id] = 'F';
            passengersFinalDestination++;
        } else {
            this.passengersSituation[id] = 'T';
            passengersInTransit++;
        }
        if (!isFinalDestination) {              // Take a Bus
            action = 'B';
        } else if  (numberOfLuggages == 0) {    // Go Home
            action = 'H';
        } else {                                // Collect a Bag
            action = 'C';
        }
        export();
        return action;
    }

    /**
     * Porter is removing luggage from the plain hold
     *
     */
    public synchronized void removeLuggageInPlainHold() {
        this.porterState = PorterStates.AT_THE_PLANES_HOLD;
        this.luggageInPlaneHold--;
        export();
    }

    /**
     * Porter is registering luggage in the conveyor belt
     *
     */
    public synchronized void registerLuggageInConveyorBelt() {
        this.porterState = PorterStates.AT_THE_LUGGAGE_BELT_CONVEYOR;
        this.luggageInConveyorBelt++;
        export();
    }

    /**
     * Porter is registering luggage in the storeroom
     *
     */
    public synchronized void registerLuggageInStoreRoom() {
        this.porterState = PorterStates.AT_THE_STOREROOM;
        this.luggageInStoreRoom++;
        export();
    }

    /**
     * Passenger has collected a luggage
     *
     * @param id int
     *
     */
    public synchronized void registerCollectedLuggage(int id) {
        if (this.passengersLuggageCollected[id] == -1) this.passengersLuggageCollected[id] = 0;
        this.passengersLuggageCollected[id] ++;
        this.luggageInConveyorBelt--;
        export();
    }

    /**
     * Registers the Passenger will to take a Bus
     *
     * @param id int
     *
     */
    public synchronized void registerPassengerToTakeABus(int id) {
        for (int i= 0; i < SimulPar.PASSENGERS; i++) {
            if (busWaitingQueue[i] == -1) {
                busWaitingQueue[i] = id;
                passengerStates[id] = PassengerStates.AT_THE_ARRIVAL_TRANSFER_TERMINAL;
                break;
            }
        }
        export();
    }

    /**
     * Registers that the Passenger has enter the Bus
     *
     * @param id int
     *
     */
    public synchronized void registerPassengerToEnterTheBus(int id) {
        for (int i= 0; i < SimulPar.BUS_CAPACITY; i++) { //Search for an empty spot on the bus
            if (busSeats[i] == -1) {
                busSeats[i] = id;  // Add to bus
                break;
            }
        }

        for (int i= 0; i < SimulPar.PASSENGERS-1; i++) {
            busWaitingQueue[i] = busWaitingQueue[i+1];
        }
        busWaitingQueue[SimulPar.PASSENGERS-1] = -1;

        this.setPassengerState(id,PassengerStates.TERMINAL_TRANSFER);
    }

    /**
     * Removes the Passenger from the Bus
     *
     * @param id int
     *
     */
    public void removePassengerFromTheBus(int id) {
        for (int i= 0; i < SimulPar.BUS_CAPACITY; i++) {
            if (busSeats[i] == id) {
                busSeats[i] = -1;  // Add to bus
                break;
            }
        }
        export();
    }

    /****** FILE ******/

    /**
     * Exports all the logs to a file
     *
     */
    private void export() {
        String output = logInternalState();
        output = output.concat("\n");
        System.out.println(output);
        pw.write(output);
        pw.flush();
    }

    /****** LOGGING ******/

    /**
     * Header for the logs
     *
     */
    public String headerState() {
        String str = "PLANE    PORTER                  DRIVER\n";
        str = str.concat("FN BN  Stat CB SR   Stat  Q1 Q2 Q3 Q4 Q5 Q6  S1 S2 S3\n");
        str = str.concat("St1 Si1 NR1 NA1 St2 Si2 NR2 NA2 St3 Si3 NR3 NA3 St4 Si4 NR4 NA4 St5 Si5 NR5 NA5 St6 Si6 NR6 NA6");
        return str;
    }

    /**
     * Logs for the internal state of the program
     *
     */
    private String logInternalState() {
        String str = "";
        str = str.concat(String.format("%-3d%-4d%-5s%-3d%-5d%-6s",(flightNumber+1), luggageInPlaneHold, porterState.getValue(), luggageInConveyorBelt, luggageInStoreRoom, busDriverState.getValue()));
        for (int i = 0; i < SimulPar.PASSENGERS; i++) {
            if (this.busWaitingQueue[i]!=-1) {
                str = str.concat(String.format("%-3d", this.busWaitingQueue[i]));
            } else {
                str = str.concat("-  ");
            }
        }
        str = str.concat(" ");
        for (int i = 0; i < SimulPar.BUS_CAPACITY; i++) {
            if (this.busSeats[i]!=-1) {
                str = str.concat(String.format("%-3d", this.busSeats[i]));
            } else {
                str = str.concat("-  ");
            }
        }
        str = str.concat("\n");

        for (int i = 0; i < SimulPar.PASSENGERS; i++) {
            str = str.concat(String.format("%-4s", passengerStates[i].getValue()));
            String situation = "-";
            if (passengersSituation[i] == 'T') situation = "TRT";
            else if (passengersSituation[i] == 'F') situation = "FDT";
            str = str.concat(String.format("%-4s", situation));
            if (passengersLuggage[i] == -1) {
                str = str.concat(String.format("%-4s", "-"));
            } else {
                str = str.concat(String.format("%-4d", passengersLuggage[i]));
            }
            if (passengersLuggageCollected[i] == -1) {
                str = str.concat(String.format("%-4s", "-"));
            } else {
                str = str.concat(String.format("%-4d", passengersLuggageCollected[i]));
            }
        }
        return str ;
    }

    /**
     * Final report for the results
     *
     */
    private String report() {
        String str = "\n";
        str = str.concat("Final report\n");
        str = str.concat("N. of passengers which have this airport as their final destination = " + passengersFinalDestination + "\n");
        str = str.concat("N. of passengers which are in transit = " + passengersInTransit + "\n");
        str = str.concat("N. of bags that should have been transported in the the planes hold = " + (bagsTransportedInThePlaneHold + bagsLost) + "\n");
        str = str.concat("N. of bags that were lost = " + bagsLost + "\n");
        return str;
    }

    /**
     * Add the report at the end of the file
     *
     */
    public void addReport() {
        String output = report();
        System.out.println(output);
        pw.write(output);
        pw.flush();
    }
}
