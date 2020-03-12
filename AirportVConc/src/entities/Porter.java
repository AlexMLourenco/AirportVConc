package entities;

import commonInfra.*;
import sharedRegions.*;

public class Porter extends Thread {

    /**
     * Porter's state
     * @serialField state
     */
    private PorterStates state;

    /**
     * If the plane hold is empty returns true
     * @serialField planeHoldEmpty
     */
    private boolean planeHoldEmpty;

    /**
     * Bags in the hold
     * @serialField bag
     */
    private BAG bag;

    /**
     * Lounge
     * @serialField arrivalLounge
     */
    private ArrivalLounge lounge;

    /**
     * Temporary Storage
     * @serialField temporaryStorage
     */
    private TemporaryStorageArea temporaryStorage;

    /**
     * Baggage Collection Point
     * @serialField bagCollectPoint
     */
    private BaggageCollectionPoint bagCollectPoint;

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repo;

    /**
     * Porter instantiation
     *
     * @param l arrivalLounge
     * @param ts temporaryStorageArea
     * @param bcp baggageCollectionPoint
     * @param r repositoryInfo
     *
     */
    public Porter(ArrivalLounge l, TemporaryStorageArea ts, BaggageCollectionPoint bcp, RepositoryInfo r) {
        super("Porter");
        lounge = l;
        temporaryStorage = ts;
        bagCollectPoint = bcp;
        repo = r;
        state = PorterStates.WAITING_FOR_A_PLANE_TO_LAND;
    }

    /**
     * Porter's lifecycle
     */
    @Override
    public void run() {
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

    /**
     * Returns this Porter's state.
     * @return porters's current state
     */
    public PorterStates getPorterState() {
        return state;
    }

    /**
     * Sets this porter's state.
     * @param s the state to be set
     */
    public void setState(PorterStates s) {
        //StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        state = s;
    }

}
