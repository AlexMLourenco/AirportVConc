package mainProject;

import java.util.Date;

public class SimulPar {

    /**
     * Number of Passengers
     */
    public static final int PASSENGERS = 6;

    /**
     * Number of Plane Landings
     */
    public static final int LANDINGS = 5;

    /**
     * Maximum number of pieces of luggage by each passenger
     */
    public static final int LUGGAGE = 2;

    /**
     * Maximum Capacity of seating places in the bus
     */
    public static final int BUS_CAPACITY = 3;

    /**
     * Bus Driver Departure Schedule (every x millis)
     */
    public static final int BUS_SCHEDULE_MILLIS = 1000;

    /**
     * Bus Driver End Of Day at (every x millis)
     */
    public static final int BUS_END_OF_DAY_MILLIS = 5000;

    /**
     * Bus Driver sleep until restart new day (millis)
     */
    public static final int BUS_SLEEP_MILLIS= 5000;

    /**
     * Minimum milliseconds to wakeup.
     */
    public static final int MIN_SLEEP = 5;

    /**
     * Maximum milliseconds to wakeup.
     */
    public static final int MAX_SLEEP = 10;

    /**
     * Repository log filename
     */
    public static final String FILENAME = "log_" + new Date().toString().replace(' ', '_') + ".txt";
}
