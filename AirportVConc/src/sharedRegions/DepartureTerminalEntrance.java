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

    private int numberOfPassengers;

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

    public void setArrivalTerminalExit(ArrivalTerminalExit arrivalTerminalExit) {
        this.arrivalTerminalExit = arrivalTerminalExit;
    }

    public synchronized void readyToLeave() {
        notifyAll();
    }
}
