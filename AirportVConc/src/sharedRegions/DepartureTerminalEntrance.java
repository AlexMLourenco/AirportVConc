package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;

public class DepartureTerminalEntrance {

    private RepositoryInfo repository;

    public DepartureTerminalEntrance(RepositoryInfo repository){
        this.repository = repository;
    }

    /*****  PASSENGER  FUNCTIONS *****/

    public synchronized void prepareNextLeg(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.ENTERING_THE_DEPARTURE_TERMINAL);
    }

}
