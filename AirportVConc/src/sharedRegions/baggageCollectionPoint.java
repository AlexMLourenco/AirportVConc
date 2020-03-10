package sharedRegions;

import commonInfra.BAG;

public class baggageCollectionPoint {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private repositoryInfo repo;

    /**
     * Baggage Collection Point instantiation
     @param repo repositoryInfo
     */
    public baggageCollectionPoint(repositoryInfo repo){
        this.repo = repo;
    }

    /* Porter functions */

    /**
     * Porter takes the bag to the correct store at the end of the journey
     * @return
     */
    void carryItToAppropriateStore(BAG bag){

    }

    /* Passenger function */

    /**
     * Passenger try's to collect a bag
     * @return
     */
    void goCollectBag(){

    }

}
