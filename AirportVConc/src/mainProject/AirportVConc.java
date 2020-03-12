package mainProject;

import sharedRegions.*;
import entities.*;
import commonInfra.*;
import java.io.FileNotFoundException;

import static mainProject.SimulPar.*;

/**
 * Main thread of the program
 */
public class AirportVConc {

    /**
     * AirportVConc main's thread
     * @param args unused main args
     * @throws FileNotFoundException When file not found
     */
    public static void main(String args[]) throws FileNotFoundException {

        /** Shared Memory **/
        RepositoryInfo repositoryInfo = new RepositoryInfo();
        ArrivalLounge arrivalLounge = new ArrivalLounge(repositoryInfo);
        ArrivalTerminalExit arrivalTerminalExit = new ArrivalTerminalExit(repositoryInfo);
        ArrivalTerminalTransferQuay arrivalTerminalTransferQuay = new ArrivalTerminalTransferQuay(repositoryInfo);
        BaggageCollectionPoint baggageCollectionPoint = new BaggageCollectionPoint(repositoryInfo);
        BaggageReclaimOffice baggageReclaimOffice = new BaggageReclaimOffice(repositoryInfo);
        DepartureTerminalEntrance departureTerminalEntrance = new DepartureTerminalEntrance(repositoryInfo);
        DepartureTerminalTransferQuay departureTerminalTransferQuay = new DepartureTerminalTransferQuay(repositoryInfo);
        TemporaryStorageArea temporaryStorageArea = new TemporaryStorageArea(repositoryInfo);

        /** Entities **/
        Porter porter = new Porter(arrivalLounge, temporaryStorageArea, baggageCollectionPoint, repositoryInfo);
        BusDriver busDriver = new BusDriver(arrivalTerminalTransferQuay, departureTerminalTransferQuay, repositoryInfo);
        Passenger[] passengers = new Passenger[PASSENGERS];

        porter.start();
        busDriver.start();

        for (int i = 0; i < LANDINGS; i ++) {
            for (int j = 0; j < PASSENGERS; j ++) {
                passengers[j]=new Passenger(j,arrivalLounge, arrivalTerminalTransferQuay, arrivalTerminalExit, departureTerminalTransferQuay, departureTerminalEntrance, baggageCollectionPoint, baggageReclaimOffice);
                passengers[j].start();
            }
            for (int j = 0; j < PASSENGERS; j ++) {
                try{
                    passengers[j].join();
                } catch(InterruptedException e){}
            }
        }
    }

}
