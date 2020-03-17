package entities;

import mainProject.AirportVConc;
import mainProject.SimulPar;
import sharedRegions.*;

import java.util.Random;

public class BusDriver extends Thread {

    private long activityStarted;

    private ArrivalTerminalTransferQuay arrivalTerminalTransferQuay;
    private DepartureTerminalTransferQuay departureTerminalTransferQuay;
    private RepositoryInfo repository;

    public BusDriver(ArrivalTerminalTransferQuay arrivalTerminalTransferQuay,
                     DepartureTerminalTransferQuay departureTerminalTransferQuay,
                     RepositoryInfo repository){
        super("Bus Driver");
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
        this.repository = repository;
    }

    private void checkWorkDayEnded() {
        if ((System.nanoTime() / 1000) - activityStarted > SimulPar.BUS_END_OF_DAY_MILLIS) {
            try {
                sleep(SimulPar.BUS_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activityStarted = System.nanoTime() / 1000;
        }
    }

    private void goToDepartureTerminal(){
        try {
            sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP+1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {}
    }

    private void goToArrivalTerminal(){
        try {
            sleep((long) (new Random().nextInt(SimulPar.MAX_SLEEP - SimulPar.MIN_SLEEP+1) + SimulPar.MAX_SLEEP));
        } catch (InterruptedException e) {}
    }

    @Override
    public void run() {
        activityStarted = System.nanoTime() / 1000;
        while (repository.isKeepBusDriverAlive()) {
            this.checkWorkDayEnded();
            arrivalTerminalTransferQuay.readyToDeparture();
            this.goToDepartureTerminal();
            //Deixar os passageiros sair
            departureTerminalTransferQuay.parkTheBusAndLetPassOff();
            this.goToArrivalTerminal();



        }


        /**
         * boolean readyToGo = true;
         * boolean dayWorkEnd = true;
         * Passenger passenger;
         * Driver driver;
         *
         * while( dayWorkEnd ) {
         *
         * 	readyToGo = driver.getSchedule();
         * 	Queue<Passenger> q = new LinkedList<>();
         *
         * 	if(readyToGo && q.size > 0 && q.size <3) {
         * 		for(i=0; i<q.size;i++){
         * 			passenger = q.peek();
         * 			arrivalTerminalTransfer.announcingBusBoarding(passenger);
         * 			q.remove(passenger);
         *                }
         * 		goToDepartureTerminal();
         * 		departureTerminalTransfer.parkTheBusAndLetPassOff();
         * 		goToArrivalTerminal();
         * 		arrivalTerminalTransfer.parkTheBus();
         ** 	} else if(q.size >= 3) {
         * 		for(i=0; i<3;i++){
         * 			passenger = q.peek();
         * 			arrivalTerminalTransfer.announcingBusBoarding(passenger);
         * 			q.remove(passenger);
         * 		}
         *
         * 		goToDepartureTerminal();
         * 		departureTerminalTransfer.parkTheBusAndLetPassOff();
         * 		goToArrivalTerminal();
         * 		arrivalTerminalTransfer.parkTheBus();
         * 	}
         *
         * 	dayWorkEnd = hasDaysWorkEnded();
         *
         * }
         */
    }




}
