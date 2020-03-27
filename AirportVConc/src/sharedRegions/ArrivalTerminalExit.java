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

    private int numberOfPassengers;
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
                System.out.println("Passenger " + passenger.getIdentifier() + " WAITING");
                wait();
                System.out.println("Passenger " + passenger.getIdentifier() + " UNBLOCKED");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public synchronized void clean_up() {
        this.numberOfPassengers = 0;
    }

    public synchronized int getNumberOfPassengers(){
        return numberOfPassengers;
    }

    public void setDepartureTerminalEntrance(DepartureTerminalEntrance departureTerminalEntrance) {
        this.departureTerminalEntrance = departureTerminalEntrance;
    }
    public synchronized void readyToLeave() {
        notifyAll();
    }
}
