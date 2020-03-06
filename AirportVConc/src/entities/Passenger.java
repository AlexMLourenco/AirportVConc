package entities;

public class Passenger extends Tread {

    /**
     * Passenger's state
     * @serialField state
     */
    private PassengerStates state;

    /**
     * Passenger's identification
     * @serialField id
     */
    private int id;

    /**
     * Passenger instantiation
     *
     * @param id Passenger id
     * @param
     * @param
     * @param
     *
     */
    public Passanger(int id){

    }

    /**
     * Passenger's lifecycle
     */
    @Override
    public void run() {

    }

    /**
     * Returns this passenger's state.
     * @return passenger's current state
     */
    public PassengerStates getPassengerState() {
        return state;
    }

    /**
     * Sets this passenger's state.
     * @param s the state to be set
     */
    public void setState(PassengerStates s) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        state = s;
    }

    /**
     * Returns this passenger's id.
     * @return passenger's id
     */
    public int getPassengerID() {
        return id;
    }

}
