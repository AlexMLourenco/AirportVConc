package sharedRegions;

import entities.*;
import mainProject.*;
import java.io.*;
import java.util.*;

public class RepositoryInfo {

    private File file;
    private PrintWriter printWriter;

    /**** Flight ****/
    int flightNumber;                   //Number of the flight
    int passengersCount;                //Number of Passengers that have landed

    /**** Entities Information ****/
    PorterStates porterState;           //State of the Porter
    BusDriverStates busDriverState;     //State of the Bus Driver
    PassengerStates[] passengerStates;  //State of the Passenger

    /**** Threads Information ****/
    boolean keepBusDriverAlive;         //Keep the Bus Driver Thread Alive
    boolean keepPorterAlive;            //Keep the Porter Thread Alive


    /**** Memory Regions ****/

    //Arrival Lounge
    int luggageInPlaneHold;             //Number of Luggages in the plain hold
    char passengersSituation[];         //Passengers situation
    int passengersLuggage[];            //Luggage that the passengers brought in the begging of the journey
    int passengersLuggageCollected[];   //Luggage that was collected by the passengers

    //BaggageCollectionPoint
    int luggageInConveyorBelt;          //Number of Luggages in the conveyor belt

    //TemporaryStorageArea
    int luggageInStoreRoom;             //Number of Luggages in the store room

    //ArrivalTerminalTransferQuay
    int busWaitingQueue[];              //Passengers Waiting Queue
    int busSeats[];                     //Passengers Seated on the Bus

    public RepositoryInfo()  {
        this.setKeepPorterAlive(true);
        this.setKeepBusDriverAlive(true);
    }

    public synchronized void init_repository(int flightNumber) {
        this.flightNumber = flightNumber;
        this.passengersCount = 0;
        this.luggageInPlaneHold = 0;
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

    public void openWriter () throws IOException {
        file = new File(SimulPar.FILENAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        printWriter = new PrintWriter(file);
    }

    public void closeWriter() {
        printWriter.close();
    }


    /***** GETTERS AND SETTERS ****/

    public synchronized int getPassengersCount() {
        return passengersCount;
    }

    /***** ACTIONS *******/

    public synchronized char passengerArrived(int id, boolean isFinalDestination, int numberOfLuggages) {
        char action;
        this.passengersCount++;
        this.luggageInPlaneHold+= numberOfLuggages;
        this.passengersLuggage[id] = numberOfLuggages;
        this.passengerStates[id] = PassengerStates.WHAT_SHOULD_I_DO;
        if (isFinalDestination)
            this.passengersSituation[id] = 'F';
        else
            this.passengersSituation[id] = 'T';
        if (!isFinalDestination) {              // Take a Bus
            action = 'B';
        } else if  (numberOfLuggages == 0) {    // Go Home
            action = 'H';
        } else {                                // Collect a Bag
            action = 'C';
        }
        return action;
    }

    public synchronized void removeLuggageInPlainHold() {
        this.porterState = PorterStates.AT_THE_PLANES_HOLD;
        this.luggageInPlaneHold--;
        this.logInternalState();
    }

    public synchronized void removeLuggageInConveyorBelt() {
        this.luggageInConveyorBelt--;
        this.logInternalState();
    }

    public synchronized void registerLuggageInConveyorBelt() {
        this.porterState = PorterStates.AT_THE_LUGGAGE_BELT_CONVEYOR;
        this.luggageInConveyorBelt++;
        this.logInternalState();
    }

    public synchronized void removeLuggageInStoreRoom() {
        this.luggageInStoreRoom--;
        this.logInternalState();
    }

    public synchronized void registerLuggageInStoreRoom() {
        this.porterState = PorterStates.AT_THE_STOREROOM;
        this.luggageInStoreRoom++;
        this.logInternalState();
    }

    public synchronized void registerCollectedLuggage(int id) {
        if (this.passengersLuggageCollected[id] == -1) this.passengersLuggageCollected[id] = 0;
        this.passengersLuggageCollected[id] ++;
    }

    public synchronized void registerPassengerToTakeABus(int id) {
        for (int i= 0; i < SimulPar.PASSENGERS; i++) {
            if (busWaitingQueue[i] == -1) {
                busWaitingQueue[i] = id;
                passengerStates[id] = PassengerStates.AT_THE_ARRIVAL_TRANSFER_TERMINAL;
                break;
            }
        }
    }

    /****** LOGGING ******/
    private void log() {
        String output = logInternalState();
        printWriter.write(output);
        printWriter.flush();
    }

    private String logInternalState() {
        String str = "PLANE    PORTER                  DRIVER\n";
        str = str.concat("FN BN  Stat CB SR   Stat  Q1 Q2 Q3 Q4 Q5 Q6  S1 S2 S3\n");
        str = str.concat(String.format("%-3d%-4d%-5s%-3d%-5d%-6s",flightNumber, luggageInPlaneHold, porterState.getValue(), luggageInConveyorBelt, luggageInStoreRoom, busDriverState.getValue()));
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


        str = str.concat("St1 Si1 NR1 NA1 St2 Si2 NR2 NA2 St3 Si3 NR3 NA3 St4 Si4 NR4 NA4 St5 Si5 NR5 NA5 St6 Si6 NR6 NA6\n");

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
        str = str.concat("\n\n");

        System.out.println(str);

        return str ;
    }

    public synchronized void setKeepBusDriverAlive(boolean keepBusDriverAlive) {
        this.keepBusDriverAlive = keepBusDriverAlive;
    }

    public synchronized void setKeepPorterAlive(boolean keepPorterAlive) {
        this.keepPorterAlive = keepPorterAlive;
    }

    public synchronized boolean isKeepBusDriverAlive() {
        return keepBusDriverAlive;
    }

    public synchronized boolean isKeepPorterAlive() {
        return keepPorterAlive;
    }
}
