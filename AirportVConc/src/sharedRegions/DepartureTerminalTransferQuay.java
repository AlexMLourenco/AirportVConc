package sharedRegions;

import java.util.Random;

import entities.BusDriver;
import entities.BusDriverStates;
import mainProject.SimulPar;

public class DepartureTerminalTransferQuay {

    private RepositoryInfo repository;
    private int passengersOnTheBus;

    public DepartureTerminalTransferQuay(RepositoryInfo repository){
        this.repository = repository;
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized void leaveTheBus(int id){
        try {
            wait();
            this.passengersOnTheBus--;
            if (this.passengersOnTheBus == 0 ) {
                notifyAll(); //Notify the driver
            }
            repository.removePassengerFromTheBus(id);
        }catch(InterruptedException e){}
    }

    /***** BUS DRIVER FUNCTIONS *********/

    public synchronized void parkTheBusAndLetPassOff(){
        repository.setBusDriverState(BusDriverStates.PARKING_AT_THE_DEPARTURE_TERMINAL);
        BusDriver busDriver = (BusDriver) Thread.currentThread();
        passengersOnTheBus = busDriver.getPassengersInTheBus();
        notifyAll(); //Notify the passengers that they can start leaving the bus
        try {
            wait(); //wait for the last passenger to leave the bus
        } catch (Exception e ) {

        }
    }

    public synchronized void goToArrivalTerminal(){
        repository.setBusDriverState(BusDriverStates.DRIVING_BACKWARD);
        try {
            Thread.currentThread().sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP+1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {}
    }


}
