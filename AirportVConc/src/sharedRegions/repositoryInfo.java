package sharedRegions;

import entities.BusDriverStates;
import entities.PassengerStates;
import entities.PorterStates;
import mainProject.SimulPar;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class repositoryInfo {

    private File f;
    private PrintWriter pw;

    // Porter
    private PorterStates porterState;

    // Passenger
    private PassengerStates[] passengerState;
    private char[] flightState;                 // T - transit, E - end
    private int[] numBags;                      // 0, 1 or 2

    // Bus Driver
    private BusDriverStates driverState;

    /**
     * Repository instantiation.
     * @throws FileNotFoundException when there's no file
     */
    public repositoryInfo() throws FileNotFoundException {
        f = new File(SimulPar.filename);
        pw = new PrintWriter(f);

        porterState = PorterStates.WAITINGPALNELAND;

        passengerState = new PassengerStates[SimulPar.Passenger];
        for (int i = 0; i < SimulPar.Passenger; i++) passengerState[i] = PassengerStates.DISENBARKINGZONE;

    }

    /**
     * Closes the printwriter when the simulation ends
     */
    public void closeWriter() {
        pw.close();
    }

    /**
     * @param porterState state of the porter
     */
    public synchronized void setPorterState(PorterStates porterState) {
        if (this.porterState != porterState) {
            this.porterState = porterState;
            export();
        }
    }

    /**
     * @param id passenger id
     * @param passengerState state of the passenger
     */
    public synchronized void setPassengerState(int id, PassengerStates passengerState, boolean export) {
        if (this.passengerState[id] != passengerState) {
            this.passengerState[id] = passengerState;
            if(export) export();
        }
    }

    /**
     * @param driverState state of the bus driver
     */
    public synchronized void setDriverState(BusDriverStates driverState) {
        if (this.driverState != driverState) {
            this.driverState = driverState;
            export();
        }
    }

    /**
     * Prints the internal state and also saves it to a file.
     */
    private void export() {
        String output = getInternalState();
        System.out.println(output);
        pw.write(output);
        pw.flush();
    }

    /**
     * Builds the string that represents the internal state.
     * @return internal state of the problem as a string
     */
    private String getInternalState() {
        return "";
    }
}
