package entities;

import mainProject.SimulPar;
import sharedRegions.ArrivalLounge;
import sharedRegions.ArrivalTerminalExit;
import sharedRegions.ArrivalTerminalTransferQuay;
import sharedRegions.BaggageCollectionPoint;
import sharedRegions.BaggageReclaimOffice;
import sharedRegions.DepartureTerminalEntrance;
import sharedRegions.DepartureTerminalTransferQuay;

public class Passenger extends Thread {
    private int identifier;
    private boolean isFinalDestination;
    private int numberOfLuggages;
    private int numberOfCollectedLuggages;

    private ArrivalLounge arrivalLounge;
    private ArrivalTerminalTransferQuay arrivalTerminalTransferQuay;
    private ArrivalTerminalExit arrivalTerminalExit;
    private DepartureTerminalTransferQuay departureTerminalTransferQuay;
    private DepartureTerminalEntrance departureTerminalEntrance;
    private BaggageCollectionPoint baggageCollectionPoint;
    private BaggageReclaimOffice baggageReclaimOffice;

    /**
     * Passenger instantiation
     *
     * @param identifier int
     * @param numberOfLuggages int
     * @param isFinalDestination boolean
     * @param arrivalLounge ArrivalLounge
     * @param arrivalTerminalTransferQuay ArrivalTerminalTransferQuay
     * @param arrivalTerminalExit ArrivalTerminalExit
     * @param departureTerminalTransferQuay DepartureTerminalTransferQuay
     * @param departureTerminalEntrance DepartureTerminalEntrance
     * @param baggageCollectionPoint BaggageCollectionPoint
     * @param baggageReclaimOffice BaggageReclaimOffice
     *
     */
    public Passenger(int identifier,
                     int numberOfLuggages,
                     boolean isFinalDestination,
                     ArrivalLounge arrivalLounge,
                     ArrivalTerminalTransferQuay arrivalTerminalTransferQuay,
                     ArrivalTerminalExit arrivalTerminalExit,
                     DepartureTerminalTransferQuay departureTerminalTransferQuay,
                     DepartureTerminalEntrance departureTerminalEntrance,
                     BaggageCollectionPoint baggageCollectionPoint,
                     BaggageReclaimOffice baggageReclaimOffice){

        super("Passenger "+ identifier);
        this.identifier = identifier;
        this.numberOfLuggages = numberOfLuggages;
        this.isFinalDestination = isFinalDestination;
        this.numberOfCollectedLuggages = 0;
        this.arrivalLounge = arrivalLounge;
        this.arrivalTerminalTransferQuay = arrivalTerminalTransferQuay;
        this.arrivalTerminalExit = arrivalTerminalExit;
        this.departureTerminalTransferQuay = departureTerminalTransferQuay;
        this.departureTerminalEntrance = departureTerminalEntrance;
        this.baggageCollectionPoint = baggageCollectionPoint;
        this.baggageReclaimOffice = baggageReclaimOffice;
    }

    /**
     * Identifier of the Passenger
     * */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Passenger's lifecycle
     */
    @Override
    public void run() {
        char action = arrivalLounge.whatShouldIDo(identifier, isFinalDestination, numberOfLuggages);

        switch (action) {
            case 'B':  // B - Take a bus
                arrivalTerminalTransferQuay.takeABus(identifier);
                arrivalTerminalTransferQuay.waitForBus(identifier);
                arrivalTerminalTransferQuay.enterTheBus(identifier);
                departureTerminalTransferQuay.leaveTheBus(identifier);
                departureTerminalEntrance.prepareNextLeg();
                break;
            case 'C':   // C - Collect Bag
                baggageCollectionPoint.goCollectBag();
                if (this.numberOfCollectedLuggages != numberOfLuggages) {
                    baggageReclaimOffice.reportMissingBag();
                }
                arrivalTerminalExit.goHome();
                break;
            case 'H':   // H - Go Home
                arrivalTerminalExit.goHome();
                break;
        }
    }

    /**
     * Increase the number of luggage collected
     * */
    public void increaseCollectedLuggages() {
        this.numberOfCollectedLuggages++;
    }

}
