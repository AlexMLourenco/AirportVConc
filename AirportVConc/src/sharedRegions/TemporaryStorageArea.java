package sharedRegions;

import commonInfra.BAG;

public class TemporaryStorageArea {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repo;

    /**
     * Temporary Storage Area instantiation
     @param repo repositoryInfo
     */
    public TemporaryStorageArea(RepositoryInfo repo){
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
