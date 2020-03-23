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

    /**
     * Bus Driver instantiation
     *
     * @param arrivalTerminalTransferQuay ArrivalTerminalTransferQuay
     * @param departureTerminalTransferQuay DepartureTerminalTransferQuay
     *
     */
    public BusDriver(ArrivalTerminalTransferQuay arrivalTerminalTransferQuay,
                     DepartureTerminalTransferQuay departureTerminalTransferQuay){
        super("Bus Driver");
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
        this.keepAlive = true;
    }

    /**
     * Check if the day of work of the Bus Driver has come to an end.
     * If it does the thread goes to sleep and wakes up a sleep_time later.
     * */
    private void checkWorkDayEnded() {
        if ((System.nanoTime() / 1000) - activityStarted > SimulPar.BUS_END_OF_DAY_MILLIS) {
            try {
                sleep(SimulPar.BUS_SLEEP_MILLIS);
            } catch (InterruptedException e) { e.printStackTrace(); }
            activityStarted = System.nanoTime() / 1000;
        }
    }

    /**
     * Bus Driver's lifecycle
     */
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

    /**
     * Get the passengers in the Bus
     */
    public int getPassengersInTheBus() {
        return passengersInTheBus;
    }

    /**
     * Set passengers in the Bus
     *
     * @param passengersInTheBus int
     */
    public void setPassengersInTheBus(int passengersInTheBus) {
        this.passengersInTheBus = passengersInTheBus;
    }

    /**
     * Boolean to check if the Bus Driver's thread continues alive or not
     *
     * @param keepAlive boolean
     */
    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}

