package entities;

import sharedRegions.*;

public class BusDriver extends Thread {

    /**
     * BusDriver's state
     * @serialField state
     */
    private BusDriverStates state;

    /**
     * Arrival Terminal Transfer Quay
     * @serialField arrTermTransfer
     */
    private ArrivalTerminalTransferQuay arrTermTransfer;

    /**
     * Departure Terminal Transfer Quay
     * @serialField depTermTransfer
     */
    private DepartureTerminalTransferQuay depTermTransfer;

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repo;

    /**
     * BusDriver instantiation
     *
     * @param att arrivalTerminalTransferQuay
     * @param dtt departureTerminalTransferQuay
     * @param r repositoryInfo
     *
     */
    public BusDriver(ArrivalTerminalTransferQuay att, DepartureTerminalTransferQuay dtt, RepositoryInfo r){
        //super("BusDriver");
        att = arrTermTransfer;
        dtt = depTermTransfer;
        repo = r;
        state = BusDriverStates.PARKING_AT_THE_ARRIVAL_TERMINAL;
    }

    /**
     * BusDriver's lifecycle
     */
    @Override
    public void run() {

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

    /**
     * Returns this BusDriver's state
     * @return BusDriver's current state
     */
    public BusDriverStates getBusDriverState() {
        return state;
    }

    /**
     * Sets this BusDriver's state
     * @param s the state to be set
     */
    public void setState(BusDriverStates s) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        state = s;
    }

    /**
     * Driver goes to the Departure Terminal
     *
     */
    /**public void goToDepartureTerminal(){

    }*/

    /**
     * Driver goes to the Arrival Terminal
     *
     */
    /**public void goToArrivalTerminal(){

    }*/

}
