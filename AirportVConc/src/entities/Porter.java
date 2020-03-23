package entities;

import commonInfra.BAG;
import sharedRegions.ArrivalLounge;
import sharedRegions.BaggageCollectionPoint;
import sharedRegions.TemporaryStorageArea;

public class Porter extends Thread {

    private ArrivalLounge arrivalLounge;
    private TemporaryStorageArea temporaryStorageArea;
    private BaggageCollectionPoint baggageCollectionPoint;
    private boolean keepAlive;

    public Porter(ArrivalLounge arrivalLounge,
                  TemporaryStorageArea temporaryStorageArea,
                  BaggageCollectionPoint baggageCollectionPoint ) {
        super("Porter");
        this.arrivalLounge= arrivalLounge;
        this.temporaryStorageArea= temporaryStorageArea;
        this.baggageCollectionPoint = baggageCollectionPoint;
        this.keepAlive = true;
    }

    @Override
    public void run() {
        Boolean planeHoldEmpty;
        while (true) {
            arrivalLounge.takeARest();
            if (!this.keepAlive) break;

            planeHoldEmpty = arrivalLounge.noMoreBagsToCollect();
            if (planeHoldEmpty) baggageCollectionPoint.warningNoMoreBagsInThePlaneHold();

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
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
