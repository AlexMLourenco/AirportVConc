package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;

public class DepartureTerminalEntrance {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Departure Terminal Entrance instantiation
     *
     @param repository repositoryInfo
     *
     */
    public DepartureTerminalEntrance(RepositoryInfo repository){
        this.repository = repository;
    }

    /*****  PASSENGER  FUNCTIONS *****/

    /**
     * Passenger is in the departure terminal to prepare the next Leg of the journey
     *
     */
    public synchronized void prepareNextLeg(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.ENTERING_THE_DEPARTURE_TERMINAL);
    }

}
