package sharedRegions;

public class DepartureTerminalEntrance {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repo;

    /**
     * Departure Terminal Entrance instantiation
     @param repo repositoryInfo
     */
    public DepartureTerminalEntrance(RepositoryInfo repo){
        this.repo = repo;
    }

    /* Passenger functions */

    /**
     * Passenger prepares the next
     * @return
     */
    void prepareNextLeg(){

    }

}
