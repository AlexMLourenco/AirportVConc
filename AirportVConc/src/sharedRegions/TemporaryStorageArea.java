package sharedRegions;

import commonInfra.BAG;
import java.util.*;

public class TemporaryStorageArea {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Queue of bags in the storeroom
     * @serialField bags
     */
    private Queue<BAG> bags;

    /**
     * Temporary Storage Area
     *
     @param repository repositoryInfo
     *
     */
    public TemporaryStorageArea(RepositoryInfo repository){
        this.repository = repository;
        this.bags = new LinkedList<>();
    }

    /***** PORTER FUNCTIONS *********/

    /**
     * Porter carry's the bag to the Appropriate Store
     *
     @param bag BAG
     *
     */
    public synchronized void carryItToAppropriateStore(BAG bag) {
        this.bags.add(bag);
        this.repository.registerLuggageInStoreRoom();
    }
}
