package entities;

import mainProject.*;
import sharedRegions.*;

import java.util.*;

public class Passenger extends Thread {
    private int identifier;
    private int flight;
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
     * @param identifier
     * @param flight
     * @param arrivalLounge BaggageCollectionPoint
     * @param arrivalTerminalTransferQuay
     * @param arrivalTerminalExit
     * @param departureTerminalTransferQuay
     * @param departureTerminalEntrance
     * @param baggageCollectionPoint
     * @param baggageReclaimOffice
     *
     */
    public Passenger(int identifier,
                     int flight,
                     ArrivalLounge arrivalLounge,
                     ArrivalTerminalTransferQuay arrivalTerminalTransferQuay,
                     ArrivalTerminalExit arrivalTerminalExit,
                     DepartureTerminalTransferQuay departureTerminalTransferQuay,
                     DepartureTerminalEntrance departureTerminalEntrance,
                     BaggageCollectionPoint baggageCollectionPoint,
                     BaggageReclaimOffice baggageReclaimOffice){
        super("Passenger "+ identifier);
        this.identifier = identifier;
        this.flight = flight;
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
        isFinalDestination = (Math.random() < 0.5);
        numberOfLuggages = new Random().nextInt(SimulPar.LUGGAGE+1);

        char action = arrivalLounge.whatShouldIDo(identifier, isFinalDestination, numberOfLuggages);

        //actions
        // B - Take a bus
        // C - Collect Bag
        // H - Go Home

        switch (action) {
            case 'B':
                arrivalTerminalTransferQuay.takeABus(identifier);
                arrivalTerminalExit.enterTheBus();
                departureTerminalTransferQuay.leaveTheBus();
                departureTerminalEntrance.prepareNextLeg();
                break;
            case 'C':
                baggageCollectionPoint.goCollectBag();
                if (this.numberOfCollectedLuggages != numberOfLuggages) {
                    baggageReclaimOffice.reportMissingBag();
                }
                arrivalTerminalExit.goHome();
                break;
            case 'H':
                arrivalTerminalExit.goHome();
                break;

        }
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

    public void increaseCollectedLuggages() {
        this.numberOfCollectedLuggages++;
    }

}
