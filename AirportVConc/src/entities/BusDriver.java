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
     * @serialField arrivalTerminalTransferQuay
     */
    private ArrivalTerminalTransferQuay arrivalTerminalTransferQuay;

    /**
     * Departure Terminal Transfer Quay
     * @serialField departureTerminalTransferQuay
     */
    private DepartureTerminalTransferQuay departureTerminalTransferQuay;

    /**
     * BusDriver instantiation
     *
     * @param arrivalTerminalTransferQuay arrivalTerminalTransferQuay
     * @param departureTerminalTransferQuay departureTerminalTransferQuay
     *
     */
    public BusDriver(ArrivalTerminalTransferQuay arrivalTerminalTransferQuay,
                     DepartureTerminalTransferQuay departureTerminalTransferQuay){
        super("Bus Driver");
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
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
