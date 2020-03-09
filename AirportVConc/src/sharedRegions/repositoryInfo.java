package sharedRegions;

import mainProject.AirportVConc;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class repositoryInfo {

    private File f;
    private PrintWriter pw;

    /**
     * Repository instantiation.
     * @throws FileNotFoundException when there's no file
     */
    public repositoryInfo() throws FileNotFoundException {
        f = new File(AirportVConc.filename);
        pw = new PrintWriter(f);


    }

    /**
     * Closes the printwriter when the simulation ends
     */
    public void closeWriter() {
        pw.close();
    }
}
