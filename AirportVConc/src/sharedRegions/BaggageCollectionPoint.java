package sharedRegions;

import java.util.LinkedList;
import java.util.Queue;

import commonInfra.BAG;
import entities.Passenger;
import entities.PassengerStates;
import entities.PorterStates;

public class BaggageCollectionPoint {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Queue of all the bags
     * @serialField bags
     */
    private Queue<BAG> bags;

    /**
     * Signal that tells us if there are more bags at the plane hold or not
     * @serialField noMoreBagsInThePlaneHold
     */
    private boolean noMoreBagsInThePlaneHold;

    /**
     * Baggage Collection Point instantiation
     *
     @param repository repositoryInfo
     *
     */
    public BaggageCollectionPoint(RepositoryInfo repository){
        this.repository = repository;
        this.bags = new LinkedList<>();
        this.noMoreBagsInThePlaneHold = false;
    }

    /***** PORTER FUNCTIONS *********/

    /**
     * Porter carry's the bag to the Appropriate Store
     *
     @param bag BAG
     *
     */
    public synchronized void carryItToAppropriateStore(BAG bag){
        this.bags.add(bag);
        repository.registerLuggageInConveyorBelt();
        notifyAll(); //Notify that a new bag is in the belt (this will wake up the passengers)
    }

    /**
     * Let the Porter know that are no more bags to collect at the plane hold
     *
     */
    public synchronized void warningNoMoreBagsInThePlaneHold() {
        this.noMoreBagsInThePlaneHold = true;
        this.repository.setPorterState(PorterStates.WAITING_FOR_A_PLANE_TO_LAND);
        notifyAll();
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passenger goes to collect a bag
     *
     */
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
