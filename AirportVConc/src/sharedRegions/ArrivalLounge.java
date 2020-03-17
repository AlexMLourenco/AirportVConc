package sharedRegions;

import java.util.*;

import mainProject.*;
import commonInfra.BAG;

public class ArrivalLounge {

    private RepositoryInfo repository;
    Stack<BAG> planeHold;   // Change

    /**
     * Arrival Lounge instantiation
     @param repository repositoryInfo
     */
    public ArrivalLounge(RepositoryInfo repository){
        this.repository= repository;
        this.planeHold = new Stack<>();
    }

    /*****  PORTER FUNCTIONS  (Calls executed by the Porter)*****/

    public synchronized boolean takeARest(){
        // We have to wait until all the passengers got out of the plane
        while(repository.getPassengersCount()!= SimulPar.PASSENGERS){
            //System.out.println("Porter Waiting! Current Passenger count: " + repository.getPassengersCount());
            try{
                wait();
            }catch(InterruptedException e){}
        }
        return true;
    }

    public synchronized boolean noMoreBagsToCollect() {
        return planeHold.empty();
    }

    public synchronized BAG tryToCollectABag() {
        repository.removeLuggageInPlainHold();
        return planeHold.pop();
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized char whatShouldIDo(int id, boolean isFinalDestination, int numberOfLuggages) {
        //System.out.println("Passenger " + id + " arrived with " + numberOfLuggages + " luggages and final destination " + isFinalDestination);
        for (int i = 0; i < numberOfLuggages; i++) {
            planeHold.push(new BAG(id,isFinalDestination));
        }
        char action = repository.passengerArrived(id, isFinalDestination,numberOfLuggages);
        notifyAll();
        return action;
    }

    void setEndOfWork() {
        // variavel que conte os passageiros que chegam em cada voo (int)
    }

}