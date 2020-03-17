package entities;

import commonInfra.*;
import sharedRegions.*;

public class Porter extends Thread {

    private ArrivalLounge arrivalLounge;
    private TemporaryStorageArea temporaryStorageArea;
    private BaggageCollectionPoint baggageCollectionPoint;
    private RepositoryInfo repository;

    /**
     * Porter instantiation
     *
     * @param arrivalLounge ArrivalLounge
     * @param temporaryStorageArea TemporaryStorageArea
     * @param baggageCollectionPoint BaggageCollectionPoint
     *
     */
    public Porter(ArrivalLounge arrivalLounge,
                  TemporaryStorageArea temporaryStorageArea,
                  BaggageCollectionPoint baggageCollectionPoint,
                  RepositoryInfo repository) {
        super("Porter");
        this.arrivalLounge= arrivalLounge;
        this.temporaryStorageArea= temporaryStorageArea;
        this.baggageCollectionPoint = baggageCollectionPoint;
        this.repository = repository;
    }

    /**
     * Porter's lifecycle
     */
    @Override
    public void run() {
        Boolean planeHoldEmpty = false;
        while (arrivalLounge.takeARest() && repository.isKeepPorterAlive()) {

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
