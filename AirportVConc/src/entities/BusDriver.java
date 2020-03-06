package entities;

public class BusDriver extends Tread {

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
     * BusDriver instantiation
     *
     * @param id BusDriver id
     * @param
     * @param
     * @param
     *
     */
    public BusDriver(int id){

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
