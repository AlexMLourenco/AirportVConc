package sharedRegions;

import java.util.LinkedList;
import java.util.Queue;

import commonInfra.BAG;
import entities.Passenger;
import entities.PassengerStates;
import entities.PorterStates;

public class BaggageCollectionPoint {

    private RepositoryInfo repository;

    private Queue<BAG> bags;
    private boolean noMoreBagsInThePlaneHold;

    public BaggageCollectionPoint(RepositoryInfo repository){
        this.repository = repository;
        this.bags = new LinkedList<>();
        this.noMoreBagsInThePlaneHold = false;
    }

    /***** PORTER FUNCTIONS *********/

    public synchronized void carryItToAppropriateStore(BAG bag){
        this.bags.add(bag);
        repository.registerLuggageInConveyorBelt();
        notifyAll(); //Notify that a new bag is in the belt (this will wake up the passengers)
    }

    public synchronized void warningNoMoreBagsInThePlaneHold() {
        this.noMoreBagsInThePlaneHold = true;
        this.repository.setPorterState(PorterStates.WAITING_FOR_A_PLANE_TO_LAND);
        notifyAll();
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized void goCollectBag(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.AT_THE_LUGGAGE_COLLECTION_POINT);
        while(true){
            try{
                //wait until the porter places a bag in the belt or informs that there are no more bags in the plane hold
                wait();
                if (this.bags.size() != 0) {
                    if (this.bags.element().getPassenger() == passenger.getIdentifier()) {
                        this.repository.registerCollectedLuggage(this.bags.element().getPassenger());
                        this.bags.remove();
                        passenger.increaseCollectedLuggages();
                    }
                }
                if (noMoreBagsInThePlaneHold) return;

            }catch(InterruptedException e){}
        }
    }

}
