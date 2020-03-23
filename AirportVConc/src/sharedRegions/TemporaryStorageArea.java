package sharedRegions;

import commonInfra.BAG;
import java.util.*;

public class TemporaryStorageArea {

    private RepositoryInfo repository;

    private Queue<BAG> bags;

    public TemporaryStorageArea(RepositoryInfo repository){
        this.repository = repository;
        this.bags = new LinkedList<>();
    }

    /***** PORTER FUNCTIONS *********/

    public synchronized void carryItToAppropriateStore(BAG bag) {
        this.bags.add(bag);
        this.repository.registerLuggageInStoreRoom();
    }

}
