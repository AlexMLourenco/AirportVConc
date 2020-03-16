package sharedRegions;

import java.util.*;

import commonInfra.BAG;
import entities.*;

public class BaggageCollectionPoint {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    private Queue<BAG> bags;

    /**
     * Boolean to check if there are more bags in the plane hold
     * @serialField noMoreBagsInThePlaneHold
     */
    private boolean noMoreBagsInThePlaneHold;

    /**
     * Baggage Collection Point instantiation
     @param repository repositoryInfo
     */
    public BaggageCollectionPoint(RepositoryInfo repository){
        this.repository = repository;
        this.bags = new LinkedList<>();
        this.noMoreBagsInThePlaneHold = false;
    }

    /* Porter functions */

    /**
     * Porter takes the bag to the correct store at the end of the journey
     * @return
     */
    public synchronized void carryItToAppropriateStore(BAG bag){
        Porter porter = (Porter) Thread.currentThread();
        //porter.setPorterState(PorterStates.AT_THE_LUGGAGE_BELT_CONVEYOR);
        this.bags.add(bag);
        repository.registerLuggageInConveyorBelt();
        notifyAll(); //Notify that a new bag is in the belt
    }

    public synchronized void warningNoMoreBagsInThePlaneHold() {
        this.noMoreBagsInThePlaneHold = true;
        notifyAll();
    }

    /* Passenger function */

    /**
     * Passenger try's to collect a bag
     * @return
     */
    public synchronized void goCollectBag(){
        Passenger passenger = (Passenger) Thread.currentThread();

        while(!noMoreBagsInThePlaneHold){
            try{
                wait();
                if (this.bags.size() != 0) {
                    if (this.bags.element().getPassenger() == passenger.getIdentifier()) {
                        this.repository.registerCollectedLuggage(this.bags.element().getPassenger());
                        this.bags.remove();
                        passenger.increaseCollectedLuggages();
                    }
                }

            }catch(InterruptedException e){}
        }
    }

}
