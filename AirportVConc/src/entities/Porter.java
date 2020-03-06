package entities;

public class Porter extends Tread {

    /**
     * Porter's state
     * @serialField state
     */
    private PorterStates state;

    /**
     * Porter's identification
     * @serialField id
     */
    private int id;

    /**
     * Lounge
     * @serialField arrivalLounge
     */
    private arrivalLounge lounge;

    /**
     * Porter instantiation
     *
     * @param id Porter id
     * @param
     * @param
     * @param
     *
     */
    public Porter(int id){

    }

    /**
     * Porter's lifecycle
     */
    @Override
    public void run() {

    }

    /**
     * Returns this Porter's state.
     * @return porters's current state
     */
    public PorterStates getPorterState() {
        return state;
    }

    /**
     * Sets this porter's state.
     * @param s the state to be set
     */
    public void setState(PorterStates s) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        state = s;
    }

    /**
     * Returns this porter's id.
     * @return porter's id
     */
    public int getPorterID() {
        return id;
    }

}
