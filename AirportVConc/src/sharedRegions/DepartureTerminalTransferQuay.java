package sharedRegions;

public class DepartureTerminalTransferQuay {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repository;

    /**
     * Departure Terminal Transfer Quay instantiation
     @param repository repositoryInfo
     */
    public DepartureTerminalTransferQuay(RepositoryInfo repository){
        this.repository = repository;
    }

    /* Passenger functions */

    /**
     * Passenger leaves the bus
     * @return
     */
    public void leaveTheBus(){

    }

    /**
     * Driver parks the bus and let the passengers off
     * @return
     */
    void parkTheBus(){

    }

}
