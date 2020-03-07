package entities;

public class Porter extends Tread {

    /**
     * Porter's state
     * @serialField state
     */
    private PorterStates state;

    /**
     * Porter's identification
     * @serialField id
     */
    private int id;

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
    private arrivalLounge lounge;

    /**
     * Temporary Storage
     * @serialField temporaryStorage
     */
    private temporaryStorageArea temporaryStorage;

    /**
     * Baggage Collection Point
     * @serialField bagCollectPoint
     */
    private baggageCollectionPoint bagCollectPoint;

    /**
     * General Repository of Information
     * @serialField repo
     */
    private repositoryInfo repo;

    /**
     * Porter instantiation
     *
     * @param id Porter id
     * @param l arrivalLounge
     * @param ts temporaryStorageArea
     * @param bcp baggageCollectionPoint
     * @param r repositoryInfo
     *
     */
    public Porter(int id, arrivalLounge l, temporaryStorageArea ts, baggageCollectionPoint bcp, repositoryInfo r) {
        super("Porter "+id);
        this.id = id;
        lounge = l;
        temporaryStorage = ts;
        bagCollectPoint = bcp;
        repo = r;
        state = PorterStates.WAITINGPALNELAND;
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
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        state = s;
    }

    /**
     * Returns this porter's id.
     * @return porter's id
     */
    public int getPorterID() {
        return id;
    }

}
