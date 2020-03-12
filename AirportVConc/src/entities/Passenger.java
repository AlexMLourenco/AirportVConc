package entities;

import mainProject.SimulPar;
import sharedRegions.*;

import java.util.Random;

public class Passenger extends Thread {

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

    private boolean isFinalDestination;
    private int numberOfLuggages;


    /**
     * Lounge
     * @serialField arrivalLounge
     */
    private ArrivalLounge arrivalLounge;

    /**
     * Arrival Terminal Transfer Quay
     * @serialField arrTermTransfer
     */
    private ArrivalTerminalTransferQuay arrTermTransfer;

    /**
     * Arrival Terminal Exit
     * @serialField arrTermExit
     */
    private ArrivalTerminalExit arrTermExit;

    /**
     * Departure Terminal Transfer Quay
     * @serialField depTermTransfer
     */
    private DepartureTerminalTransferQuay depTermTransfer;

    /**
     * Departure Terminal Entrance
     * @serialField depTermEntrance
     */
    private DepartureTerminalEntrance depTermEntrance;

    /**
     * Baggage Collection Point
     * @serialField bagCollectPoint
     */
    private BaggageCollectionPoint bagCollectPoint;

    /**
     * Baggage Reclaim Office
     * @serialField bagReclaimOffice
     */
    private BaggageReclaimOffice bagReclaimOffice;

    /**
     * Passenger instantiation
     *  @param id Passenger id
     * @param l arrivalLounge
     * @param att arrivalTerminalTransferQuay
     * @param ate arrivalTerminalExit
     * @param dtt departureTerminalTransferQuay
     * @param dte departureTerminalEntrance
     * @param bcp baggageCollectionPoint
     * @param bro baggageReclaimOffice
     *
     */
    public Passenger(int id, ArrivalLounge l, ArrivalTerminalTransferQuay att, ArrivalTerminalExit ate, DepartureTerminalTransferQuay dtt, DepartureTerminalEntrance dte, BaggageCollectionPoint bcp, BaggageReclaimOffice bro){
        //super("Passanger "+id);
        this.id = id;
        arrivalLounge = l;
        arrTermTransfer = att;
        arrTermExit = ate;
        depTermTransfer = dtt;
        depTermEntrance = dte;
        bagCollectPoint = bcp;
        bagReclaimOffice = bro;
        state = PassengerStates.AT_THE_DISEMBARKING_ZONE; // What Should I Do?
    }

    /**
     * Passenger's lifecycle
     */
    @Override
    public void run() {
        isFinalDestination = (Math.random() < 0.5);
        numberOfLuggages = new Random().nextInt(SimulPar.LUGGAGE+1);
        //Criar uma stack no arrival lounge, que contenha as malas de todos os passageiros (simular o porao)
        //notificar o bagageiro que tem malas para aviar.

        /***
        Boolean isFinalDst = false
        Passenger passenger;
        int numBags;
        int voo, npass, search;
        BAG bag;

        for (voo = 0; voo < 5; voo++) {
            if(arrivalLounge.whatShouldIDo() == 'T') {		// In transit
                arrivalTerminalTransfer.takeABus();			// passenger
                arrivalTerminalExit.enterTheBus();
                departureTerminalTransfer.leaveTheBus();
                departureTerminalEntrance.prepareNextLeg();
            } else {	// End
                numBags = 0;
                while(numBags < nBagsDesp[voo]){
                    bag = baggageCollectionPoint.goCollectBag();
                    if(bag == null) baggageReclaimOffice.reportMissingBags();
                    numBags++;
                }
                arrivalTerminalExit.goHome();
            }
        }**/


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
