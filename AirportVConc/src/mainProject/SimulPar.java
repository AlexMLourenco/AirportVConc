package mainProject;

import java.util.Date;

public class SimulPar {

    /**
     * Number of Passengers
     */
    public static final int Passenger = 6;

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
