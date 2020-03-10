package sharedRegions;

import commonInfra.FIFO;

public class arrivalTerminalTransferQuay {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private repositoryInfo repo;

    /**
     * Passengers waiting for a bus at the disembarking zone
     * @serialField waitingForBus
     */
    private FIFO waitingForBus;

    /**
     * Arrival Terminal Transfer Quay instantiation
     @param repo repositoryInfo
     */
    public arrivalTerminalTransferQuay(repositoryInfo repo){
        this.repo = repo;
    }

    /* Passenger functions */

    /**
     * Passenger take a bus
     * @return
     */
    void takeABus(){

    }

    /**
     * Driver parks the bus
     * @return
     */
    void parkTheBus(){

    }

}
