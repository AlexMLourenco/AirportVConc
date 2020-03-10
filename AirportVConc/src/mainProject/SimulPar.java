package mainProject;

import java.util.Date;

public class SimulPar {

    /**
     * Number of Passengers
     */
    public static final int Passenger = 6;

    /**
     * Number of Plane Landings
     */
    public static final int Landings = 5;

    /**
     * Maximum number of pieces of luggage by each passenger
     */
    public static final int Luggage = 2;

    /**
     * Maximum Capacity of seating places in the bus
     */
    public static final int BusCapacity = 3;

    /**
     * Minimum milliseconds to wakeup.
     */
    public static final int minSleep = 5;

    /**
     * Maximum milliseconds to wakeup.
     */
    public static final int maxSleep = 10;

    /**
     * Repository log filename
     */
    public static final String filename = "log_" + new Date().toString().replace(' ', '_') + ".txt";
}
