package entities;

public class Passenger extends Tread {

    /**
     * Passenger's state
     * @serialField state
     */
    private PassengerStates state;

    /**
     * Passenger's identification
     * @serialField id
     */
    private int id;

    /**
     * Lounge
     * @serialField arrivalLounge
     */
    private arrivalLounge lounge;

    /**
     * Arrival Terminal Transfer Quay
     * @serialField arrTermTransfer
     */
    private arrivalTerminalTransferQuay arrTermTransfer;

    /**
     * Arrival Terminal Exit
     * @serialField arrTermExit
     */
    private arrivalTerminalExit arrTermExit;

    /**
     * Departure Terminal Transfer Quay
     * @serialField depTermTransfer
     */
    private departureTerminalTransferQuay depTermTransfer;

    /**
     * Departure Terminal Entrance
     * @serialField depTermEntrance
     */
    private departureTerminalEntrance depTermEntrance;

    /**
     * Baggage Collection Point
     * @serialField bagCollectPoint
     */
    private baggageCollectionPoint bagCollectPoint;

    /**
     * Baggage Reclaim Office
     * @serialField bagReclaimOffice
     */
    private baggageReclaimOffice bagReclaimOffice;

    /**
     * Passenger instantiation
     *
     * @param id Passenger id
     * @param l arrivalLounge
     * @param att arrivalTerminalTransferQuay
     * @param ate arrivalTerminalExit
     * @param dtt departureTerminalTransferQuay
     * @param dte departureTerminalEntrance
     * @param bcp baggageCollectionPoint
     * @param bro baggageReclaimOffice
     *
     */
    public Passanger(int id, arrivalLounge l, arrivalTerminalTransferQuay att, arrivalTerminalExit ate, departureTerminalTransferQuay dtt, departureTerminalEntrance dte, baggageCollectionPoint bcp, baggageReclaimOffice bro){
        super("Passanger "+id);
        this.id = id;
        lounge = l;
        arrTermTransfer = att;
        arrTermExit = ate;
        depTermTransfer = dtt;
        depTermEntrance = dte;
        bagCollectPoint = bcp;
        bagReclaimOffice = bro;
        state = PassengerStates.DISENBARKINGZONE; // What Should I Do?
    }

    /**
     * Passenger's lifecycle
     */
    @Override
    public void run() {
        /**
         * Boolean isFinalDst = false
         * Passenger passenger;
         * int numBags;
         * int voo, npass, search;
         * BAG bag;
         *
         * 	for (voo = 0; voo < 5; voo++) {
         * 		if(arrivalLounge.whatSouldIDo() == 'T') {		// In transit
         * 			arrivalTerminalTransfer.takeABus();			// passenger
         * 			arrivalTerminalExit.enterTheBus();
         * 			departureTerminalTransfer.leaveTheBus();
         * 			departureTerminalEntrance.prepareNextLeg();
         *                } else {	// End
         * 			numBags = 0;
         * 			while(numBags < nBagsDesp[voo]){
         * 				bag = baggageCollectionPoint.goCollectBag();
         * 				if(bag == null) baggageReclaimOffice.reportMissingBags();
         * 				numBags++;
         *            }
         * 			arrivalTerminalExit.goHome();
         *        }* 	}
         */
    }

    /**
     * Returns this passenger's state.
     * @return passenger's current state
     */
    public PassengerStates getPassengerState() {
        return state;
    }

    /**
     * Sets this passenger's state.
     * @param s the state to be set
     */
    public void setState(PassengerStates s) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        state = s;
    }

    /**
     * Returns this passenger's id.
     * @return passenger's id
     */
    public int getPassengerID() {
        return id;
    }

}
