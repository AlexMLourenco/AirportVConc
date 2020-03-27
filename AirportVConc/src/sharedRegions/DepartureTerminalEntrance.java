package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;
import mainProject.SimulPar;

public class DepartureTerminalEntrance {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Integer to get the number of passengers currently waiting to leave the airport or to check in for the next leg of the journey
     * @serialField numberOfPassengers
     */
    private int numberOfPassengers;

    /**
     * Arrival Terminal Exit
     * @serialField arrivalTerminalExit
     */
    private ArrivalTerminalExit arrivalTerminalExit;

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
        numberOfPassengers++;
        if (arrivalTerminalExit.getNumberOfPassengers() + numberOfPassengers == SimulPar.PASSENGERS) {
            notifyAll();
            arrivalTerminalExit.readyToLeave();
        } else{
            try {
                wait();
            } catch (InterruptedException e) {}
        }
    }

    /**
     * Set the counter of the number of passengers waiting for their fellow passengers to zero
     *
     */
    public synchronized void clean_up() {
        this.numberOfPassengers = 0;
    }

    /**
     * Get the number of passengers waiting for their fellow passengers
     *
     */
    public synchronized int getNumberOfPassengers(){
        return numberOfPassengers;
    }

    /**
     * Set the Arrival Terminal Exit memory zone
     *
     */
    public void setArrivalTerminalExit(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
    }

    /**
     * Wake the passengers when when everyone is ready to leave the airport or check in for the next leg of the journey
     *
     */
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
