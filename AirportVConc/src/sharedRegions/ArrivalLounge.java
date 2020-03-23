package sharedRegions;

import java.util.Stack;

import commonInfra.BAG;
import mainProject.SimulPar;

public class ArrivalLounge {

    private RepositoryInfo repository;

    /*** simulation purposes only ***/
    private int[][] plainHoldLuggage;
    private boolean[][] passengersFinalDestination;

    /*** shared memory ***/
    Stack<BAG> planeHold;
    private int passengersCount;

    public ArrivalLounge(RepositoryInfo repository, int[][] plainHoldLuggage, boolean[][] passengersFinalDestination) {
        this.repository = repository;
        this.planeHold = new Stack<>();
        this.plainHoldLuggage = plainHoldLuggage;
        this.passengersFinalDestination = passengersFinalDestination;
    }

    /*****  PORTER FUNCTIONS *****/
    public synchronized void takeARest() {
        // We have to wait until all the passengers got out of the plane and have bags to collect in the plane hold
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public synchronized boolean noMoreBagsToCollect() {
        return planeHold.empty();
    }

    public synchronized BAG tryToCollectABag() {
        repository.removeLuggageInPlainHold();
        BAG b = planeHold.pop();
        return b;
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized char whatShouldIDo(int id, boolean isFinalDestination, int numberOfLuggages) {
        this.passengersCount ++;
        char action = repository.passengerArrived(id, isFinalDestination, numberOfLuggages);
        if (this.passengersCount == SimulPar.PASSENGERS) {
            notifyAll(); // The last passenger to arrive will wake up the porter.
        }
        return action;
    }


    /***** MAIN THREAD *********/

    public synchronized void init_plane_hold(int flightNumber) {
        this.passengersCount = 0;
        planeHold.clear();
        for (int i = 0; i < SimulPar.PASSENGERS; i ++) {
            for (int j = 0; j < plainHoldLuggage[flightNumber][i]; j++) {
                BAG bag = new BAG(i, passengersFinalDestination[flightNumber][i]);
                planeHold.push(bag);
            }
        }
        repository.flightLanded(planeHold.size());
    }

    public synchronized void setPorterEndOfWork() {
        notifyAll();
    }
}
