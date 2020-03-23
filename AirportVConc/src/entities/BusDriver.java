package entities;

import mainProject.SimulPar;
import sharedRegions.ArrivalTerminalTransferQuay;
import sharedRegions.DepartureTerminalTransferQuay;

public class BusDriver extends Thread {

    private long activityStarted;
    private int passengersInTheBus;
    private boolean keepAlive;

    private ArrivalTerminalTransferQuay arrivalTerminalTransferQuay;
    private DepartureTerminalTransferQuay departureTerminalTransferQuay;

    public BusDriver(ArrivalTerminalTransferQuay arrivalTerminalTransferQuay,
                     DepartureTerminalTransferQuay departureTerminalTransferQuay){
        super("Bus Driver");
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
        this.keepAlive = true;
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

    @Override
    public void run() {
        activityStarted = System.nanoTime() / 1000;
        while (true) {
            this.checkWorkDayEnded();
            if (arrivalTerminalTransferQuay.readyToDeparture()) {
                arrivalTerminalTransferQuay.announcingBusBoarding();
                arrivalTerminalTransferQuay.goToDepartureTerminal();
                departureTerminalTransferQuay.parkTheBusAndLetPassOff();
                departureTerminalTransferQuay.goToArrivalTerminal();
                arrivalTerminalTransferQuay.parkTheBus();
            }
            if (!this.keepAlive) break;
        }
    }

    public int getPassengersInTheBus() {
        return passengersInTheBus;
    }

    public void setPassengersInTheBus(int passengersInTheBus) {
        this.passengersInTheBus = passengersInTheBus;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}

