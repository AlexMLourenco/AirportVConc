package entities;

public enum BusDriverStates {
    PARKING_AT_THE_ARRIVAL_TERMINAL("PKAT"),
    DRIVING_FORWARD("DRFW"),
    PARKING_AT_THE_DEPARTURE_TERMINAL("PKDT"),
    DRIVING_BACKWARD("DRBW");

    private String value;

    BusDriverStates(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}