package models;

public class Ticket {
    private final String id;
    private final String spotId;
    private final VehicleType vehicleType;
    private final long entryTime;
    private final FareType faretype;

    public Ticket(String id, String spotId, VehicleType vehicleType, long entryTime, FareType faretype) {
        this.id = id;
        this.spotId = spotId;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.faretype = faretype;
    }

    public String getId() {
        return id;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public long getEntryTime() {
        return entryTime;
    }
    
    public FareType getFareType() {
        return faretype;
    }
}
