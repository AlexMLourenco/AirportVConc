package sharedRegions;

import commonInfra.BAG;
import java.util.*;

public class TemporaryStorageArea {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    // Change
    private Queue<BAG> bags;

    /**
     * Temporary Storage Area instantiation
     @param repository repositoryInfo
     */
    public TemporaryStorageArea(RepositoryInfo repository){
        this.repository = repository;
        this.bags = new LinkedList<>();
    }

    /* Porter functions */

    /**
     * Porter takes the bag to the correct store if it is in transit
     * @return
     */
    public synchronized void carryItToAppropriateStore(BAG bag) {
        this.bags.add(bag);
        this.repository.registerLuggageInStoreRoom();
    }

}
