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

    public int getIdentifier() {
        return identifier;
    }

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

    public void increaseCollectedLuggages() {
        this.numberOfCollectedLuggages++;
    }

}
