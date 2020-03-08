package entities;

import commonInfra.*;
import sharedRegions.*;

public class BusDriver extends Thread {

    /**
     * BusDriver's state
     * @serialField state
     */
    private BusDriverStates state;

    /**
     * BusDriver's identification
     * @serialField id
     */
    private int id;

    /**
     * Arrival Terminal Transfer Quay
     * @serialField arrTermTransfer
     */
    private arrivalTerminalTransferQuay arrTermTransfer;

    /**
     * Departure Terminal Transfer Quay
     * @serialField depTermTransfer
     */
    private departureTerminalTransferQuay depTermTransfer;

    /**
     * General Repository of Information
     * @serialField repo
     */
    private repositoryInfo repo;

    /**
     * BusDriver instantiation
     *
     * @param id BusDriver id
     * @param att arrivalTerminalTransferQuay
     * @param dtt departureTerminalTransferQuay
     * @param r repositoryInfo
     *
     */
    public BusDriver(int id, arrivalTerminalTransferQuay att, departureTerminalTransferQuay dtt, repositoryInfo r){
        //super("BusDrive "+id);
        this.id = id;
        att = arrTermTransfer;
        dtt = depTermTransfer;
        repo = r;
        state = BusDriverStates.PARKARRIVALTERMINAL;
    }

    /**
     * BusDriver's lifecycle
     */
    @Override
    public void run() {

    }

    /**
     * Returns this BusDriver's state.
     * @return BusDriver's current state
     */
    public BusDriverStates getBusDriverState() {
        return state;
    }

    /**
     * Sets this BusDriver's state.
     * @param s the state to be set
     */
    public void setState(BusDriverStates s) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        state = s;
    }

    /**
     * Returns this BusDriver's id.
     * @return BusDriver's id
     */
    public int getBusDriverID() {
        return id;
    }

}
