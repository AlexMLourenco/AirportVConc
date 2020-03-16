package entities;

import commonInfra.*;
import sharedRegions.*;

public class Porter extends Thread {

    private ArrivalLounge arrivalLounge;
    private TemporaryStorageArea temporaryStorageArea;
    private BaggageCollectionPoint baggageCollectionPoint;

    public Porter(ArrivalLounge arrivalLounge,
                  TemporaryStorageArea temporaryStorageArea,
                  BaggageCollectionPoint baggageCollectionPoint
    ) {
        super("Porter");
        this.arrivalLounge= arrivalLounge;
        this.temporaryStorageArea= temporaryStorageArea;
        this.baggageCollectionPoint = baggageCollectionPoint;

    }

    @Override
    public void run() {
        Boolean planeHoldEmpty = false;
        while (arrivalLounge.takeARest()) {

            while (!planeHoldEmpty) {
                BAG bag = arrivalLounge.tryToCollectABag();
                if (bag.isFinalDestination()) {
                    baggageCollectionPoint.carryItToAppropriateStore(bag);
                } else {
                    temporaryStorageArea.carryItToAppropriateStore(bag);
                }

                planeHoldEmpty = arrivalLounge.noMoreBagsToCollect();

                if (planeHoldEmpty) baggageCollectionPoint.warningNoMoreBagsInThePlaneHold();
            }
        }

        /**
         * BAG bag;
         * boolean planeHoldEmpty;
         *
         * while ( arrivalLounge.takeARest != 'E' ) {
         * 	planeHoldEmpty = false;
         *
         * 	while( !planeHoldEmpty ) {
         *
         * 		bag = arrivalLounge.tryToCollectABag();
         *
         * 		if (bag == null) planeHoldEmpty = true;	// Porao esta vazio
         *
         * 		else if (bag.getDestStat() == 'T') temporaryStorageArea.carryItToAppropriateStore(bag);	// if in transit
         *
         * 		else baggageCollectionPoint.carryItToAppropriateStore(bag);
         *        }
         *
         * 	arrivalLounge.noMoreBagsToCollect();
         * }
         */
    }


    private void logToConsole(String message) {
        System.out.println(Thread.currentThread().getId() + ": " + message);

    }

}
