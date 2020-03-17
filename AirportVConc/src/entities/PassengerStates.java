package entities;

public enum PassengerStates {
    WHAT_SHOULD_I_DO("WSD"),
    AT_THE_ARRIVAL_TRANSFER_TERMINAL("ATT"),
    TERMINAL_TRANSFER("TRT"),
    AT_THE_DEPARTURE_TRANSFER_TERMINAL("DTT"),
    ENTERING_THE_DEPARTURE_TERMINAL("EDT"),
    AT_THE_LUGGAGE_COLLECTION_POINT("LCP"),
    AT_THE_BAGGAGE_RECLAIM_OFFICE("BRO"),
    EXITING_THE_ARRIVAL_TERMINAL("EAT");

    private String value;

    PassengerStates(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}