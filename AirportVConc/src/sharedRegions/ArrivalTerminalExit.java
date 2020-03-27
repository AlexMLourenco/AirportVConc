package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;
import mainProject.SimulPar;

public class ArrivalTerminalExit {

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
     * Departure Terminal Entrance
     * @serialField departureTerminalEntrance
     */
    private DepartureTerminalEntrance departureTerminalEntrance;

    /**
     * Arrival Terminal Exit instantiation
     @param repository repositoryInfo
     */
    public ArrivalTerminalExit(RepositoryInfo repository){
        this.repository = repository;
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passenger goes home
     */
    public synchronized void goHome(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.EXITING_THE_ARRIVAL_TERMINAL);
        numberOfPassengers++;
        if (departureTerminalEntrance.getNumberOfPassengers() + numberOfPassengers == SimulPar.PASSENGERS) {
            notifyAll();
            departureTerminalEntrance.readyToLeave();
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
     * Set the Departure Terminal Entrance memory zone
     *
     */
    public void setDepartureTerminalEntrance(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
    }

    /**
     * Wake the passengers when when everyone is ready to leave the airport or check in for the next leg of the journey
     *
     */
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
