package mainProject;

import static mainProject.SimulPar.LANDINGS;
import static mainProject.SimulPar.PASSENGERS;

import java.util.Random;
import entities.BusDriver;
import entities.Passenger;
import entities.Porter;
import sharedRegions.ArrivalLounge;
import sharedRegions.ArrivalTerminalExit;
import sharedRegions.ArrivalTerminalTransferQuay;
import sharedRegions.BaggageCollectionPoint;
import sharedRegions.BaggageReclaimOffice;
import sharedRegions.DepartureTerminalEntrance;
import sharedRegions.DepartureTerminalTransferQuay;
import sharedRegions.RepositoryInfo;
import sharedRegions.TemporaryStorageArea;

public class AirportVConc {
    public static void main(String args[]) {
        try {

            /**
             * Shared Memory
             */
            RepositoryInfo repository = new RepositoryInfo();

            /**
             * Random generation of passenger info for simulation purposes only
             * Luggage in the plane hold, luggage lost and final destination (yes/no)
             * **/

            int[][] passengersLuggage = new int [LANDINGS] [PASSENGERS];
            boolean[][] passengersFinalDestination = new boolean [LANDINGS] [PASSENGERS];

            for (int i = 0; i < LANDINGS; i++) {
                for (int j = 0; j < PASSENGERS; j++) {
                    passengersLuggage[i][j] = new Random().nextInt(SimulPar.LUGGAGE+1);
                    passengersFinalDestination[i][j] = (Math.random() < 0.5);
                }
            }

            // Random generation of luggage LOST for each passenger (for simulation purposes)
            int[][] plainHoldLuggage = new int [LANDINGS] [PASSENGERS];

            for (int i = 0; i < LANDINGS; i++) {
                for (int j = 0; j < PASSENGERS; j++) {
                    plainHoldLuggage[i][j] = new Random().nextInt(passengersLuggage[i][j]+1);
                }
            }
            /**
             * End simulation routines
             */

            ArrivalLounge arrivalLounge = new ArrivalLounge(repository, plainHoldLuggage, passengersFinalDestination);
            ArrivalTerminalExit arrivalTerminalExit = new ArrivalTerminalExit(repository);
            ArrivalTerminalTransferQuay arrivalTerminalTransferQuay = new ArrivalTerminalTransferQuay(repository);
            BaggageCollectionPoint baggageCollectionPoint = new BaggageCollectionPoint(repository);
            BaggageReclaimOffice baggageReclaimOffice = new BaggageReclaimOffice(repository);
            DepartureTerminalEntrance departureTerminalEntrance = new DepartureTerminalEntrance(repository);
            DepartureTerminalTransferQuay departureTerminalTransferQuay = new DepartureTerminalTransferQuay(repository);
            TemporaryStorageArea temporaryStorageArea = new TemporaryStorageArea(repository);

            arrivalTerminalExit.setDepartureTerminalEntrance(departureTerminalEntrance);
            departureTerminalEntrance.setArrivalTerminalExit(arrivalTerminalExit);

            /**
             * Entities
             **/
            Porter porter = new Porter(arrivalLounge, temporaryStorageArea, baggageCollectionPoint);
            BusDriver busDriver = new BusDriver(arrivalTerminalTransferQuay, departureTerminalTransferQuay);
            Passenger[] passengers = new Passenger[PASSENGERS];

            porter.start();
            busDriver.start();

            repository.headerState();

            for (int flightNumber = 0; flightNumber < LANDINGS; flightNumber ++) {

                repository.init_repository(flightNumber);           //Reset flights info
                arrivalLounge.init_plane_hold(flightNumber);        //Create the plane hold (simulation)
                arrivalTerminalExit.clean_up();
                departureTerminalEntrance.clean_up();
                busDriver.setPassengersInTheBus(0);

                for (int j = 0; j < PASSENGERS; j ++) {
                    passengers[j]=new Passenger(j,
                                                passengersLuggage[flightNumber][j],
                                                passengersFinalDestination[flightNumber][j],
                                                arrivalLounge,
                                                arrivalTerminalTransferQuay,
                                                arrivalTerminalExit,
                                                departureTerminalTransferQuay,
                                                departureTerminalEntrance,
                                                baggageCollectionPoint,
                                                baggageReclaimOffice);
                    passengers[j].start();
                }
                for (int j = 0; j < PASSENGERS; j ++) {
                    try{
                        passengers[j].join();
                    } catch(InterruptedException e){}
                }
            }

            porter.setKeepAlive(false);
            busDriver.setKeepAlive(false);

            arrivalLounge.setPorterEndOfWork();
            arrivalTerminalTransferQuay.setBusDriverEndOfWork();

            porter.join();
            busDriver.join();

            repository.addReport();

        }  catch (Exception ex) { System.out.println(ex); }

    }

}


