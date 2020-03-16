package commonInfra;

public class BAG {

    private int passenger;
    private boolean isFinalDestination;

    public BAG(int passenger, boolean isFinalDestination) {
        this.passenger = passenger;
        this.isFinalDestination = isFinalDestination;
    }

    public int getPassenger() {
        return passenger;
    }

    public boolean isFinalDestination() {
        return isFinalDestination;
    }
}
