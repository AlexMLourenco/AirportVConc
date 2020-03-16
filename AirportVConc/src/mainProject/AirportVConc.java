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
    public static void main(String args[]) {
        try {
            /** Shared Memory **/
            RepositoryInfo repository = new RepositoryInfo();
            //repository.openWriter();
            ArrivalLounge arrivalLounge = new ArrivalLounge(repository);
            ArrivalTerminalExit arrivalTerminalExit = new ArrivalTerminalExit(repository);
            ArrivalTerminalTransferQuay arrivalTerminalTransferQuay = new ArrivalTerminalTransferQuay(repository);
            BaggageCollectionPoint baggageCollectionPoint = new BaggageCollectionPoint(repository);
            BaggageReclaimOffice baggageReclaimOffice = new BaggageReclaimOffice(repository);
            DepartureTerminalEntrance departureTerminalEntrance = new DepartureTerminalEntrance(repository);
            DepartureTerminalTransferQuay departureTerminalTransferQuay = new DepartureTerminalTransferQuay(repository);
            TemporaryStorageArea temporaryStorageArea = new TemporaryStorageArea(repository);

            /** Entities **/
            Porter porter = new Porter(arrivalLounge, temporaryStorageArea, baggageCollectionPoint);
            BusDriver busDriver = new BusDriver(arrivalTerminalTransferQuay, departureTerminalTransferQuay);
            Passenger[] passengers = new Passenger[PASSENGERS];

            porter.start();
            busDriver.start();

            for (int flightNumber = 0; flightNumber < LANDINGS; flightNumber ++) {

                repository.init_repository(flightNumber);

                for (int j = 0; j < PASSENGERS; j ++) {
                    passengers[j]=new Passenger(j,flightNumber,arrivalLounge, arrivalTerminalTransferQuay, arrivalTerminalExit, departureTerminalTransferQuay, departureTerminalEntrance, baggageCollectionPoint, baggageReclaimOffice);
                    passengers[j].start();
                }
                for (int j = 0; j < PASSENGERS; j ++) {
                    try{
                        passengers[j].join();
                    } catch(InterruptedException e){}
                }
                //repository.closeWriter();
            }
        }  catch (Exception ex) {
            System.out.println(ex);
        }

    }

}

