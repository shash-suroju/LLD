package models;

public class Vehicle {
    
    private String id;
    
    private VehicleType vehicleType;
    
    public Vehicle (String id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }
    
    public String getId() {
        return id;
    }
    
    public VehicleType getVehicleType() {
        return vehicleType;
    }
}