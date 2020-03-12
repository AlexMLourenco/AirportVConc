package sharedRegions;

public class BaggageReclaimOffice {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repo;

    /**
     * Baggage Reclaim Office instantiation
     @param repo repositoryInfo
     */
    public BaggageReclaimOffice(RepositoryInfo repo){
        this.repo = repo;
    }

    /* Passenger functions */

    /**
     * Passenger reports a missing bag
     * @return
     */
    void reportMissingBag(){

    }

}
