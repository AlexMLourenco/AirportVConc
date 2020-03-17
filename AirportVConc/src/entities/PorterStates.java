package entities;

public enum PorterStates {
    WAITING_FOR_A_PLANE_TO_LAND ("WPTL"),
    AT_THE_PLANES_HOLD("APLH"),
    AT_THE_LUGGAGE_BELT_CONVEYOR("ALCB"),
    AT_THE_STOREROOM("ASTR");

    private String value;

    PorterStates(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
