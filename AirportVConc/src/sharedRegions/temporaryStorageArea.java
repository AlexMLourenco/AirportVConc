package sharedRegions;

import commonInfra.BAG;

public class temporaryStorageArea {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private repositoryInfo repo;

    /**
     * Temporary Storage Area instantiation
     @param repo repositoryInfo
     */
    public temporaryStorageArea(repositoryInfo repo){
        this.repo = repo;
    }

    /* Porter functions */

    /**
     * Porter takes the bag to the correct store if it is in transit
     * @return
     */
    void carryItToAppropriateStore(BAG bag) {

    }

}
