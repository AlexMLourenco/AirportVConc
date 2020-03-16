package sharedRegions;

public class DepartureTerminalEntrance {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repository;

    /**
     * Departure Terminal Entrance instantiation
     @param repository repositoryInfo
     */
    public DepartureTerminalEntrance(RepositoryInfo repository){
        this.repository = repository;
    }

    /* Passenger functions */

    /**
     * Passenger prepares the next
     * @return
     */
    public void prepareNextLeg(){

    }

}
