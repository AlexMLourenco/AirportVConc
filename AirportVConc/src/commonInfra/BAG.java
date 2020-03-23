package commonInfra;

public class BAG {

    private int passenger;
    private boolean isFinalDestination;

    /**
     *   BAG instantiation.
     *
     *     @param passenger passenger
     *     @param isFinalDestination isFinalDestination
     */
    public BAG(int passenger, boolean isFinalDestination) {
        this.passenger = passenger;
        this.isFinalDestination = isFinalDestination;
    }

    /**
     *   BAG passenger retrieval.
     *
     *    @return passenger to whom the bag belongs
     */
    public int getPassenger() {
        return passenger;
    }

    /**
     *   BAG destination retrieval.
     *
     *    @return type of destination of that bag
     */
    public boolean isFinalDestination() {
        return isFinalDestination;
    }
}
