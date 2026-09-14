import java.util.*;
import models.*;
import strategy.*;

public class ParkingLotService {
    
    private final List<ParkingSpot> allSlots;
    
    private final Set<String> occupiedSlots;
    
    private final Map<FareType, FareStrategy> strategies;
    
    private final Map<String, Ticket> issuedTickets;
    
    
    
    public ParkingLotService () {
        allSlots = new ArrayList<>();
        occupiedSlots = new HashSet<>();
        
        strategies = new HashMap<>();
        strategies.put(FareType.BASIC, new BasicFareStrategy(100));
        // strategies.put(FareType.PEAK, new PeakFareStrategy());
        
        allSlots.add(new ParkingSpot("p1", SpotType.SMALL));
        allSlots.add(new ParkingSpot("p3", SpotType.LARGE));
        
        issuedTickets = new HashMap<>();
    }
    
    
    
    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = getAvailableSpot(vehicle.getVehicleType());
        
        if(spot == null) {
            System.out.println("No available Parking Spot found for Vehicle " + vehicle.getId());
            return null;
        }
        
        String id = UUID.randomUUID().toString();
        long time = System.currentTimeMillis();
        Ticket ticket = new Ticket(id, spot.getId(), vehicle.getVehicleType(), time, FareType.BASIC);
        
        // update the issued tickets
        issuedTickets.put(id, ticket);
        
        // update the occupied slots;
        occupiedSlots.add(spot.getId());
        
        System.out.println("Parking Successful for Vehicle: " + vehicle.getId());
        
        return ticket;
    }
    
    
    
    public int exitVehicle(Ticket ticket) {
        // verify ticket
        if(issuedTickets.get(ticket.getId()) == null) {
            System.out.println("Invaid ticket");
            return 0;
        }
        
        // calculatefee
        FareStrategy strategy = strategies.get(ticket.getFareType());
        int fee = strategy.calculateFare(ticket);
        
        // free the slots
        occupiedSlots.remove(ticket.getSpotId());
        
        
        // remove from issuedTickets
        issuedTickets.remove(ticket.getId());
        
        return fee;
    }
    
    
    private ParkingSpot getAvailableSpot (VehicleType vehicleType) {
        SpotType spotType = getSpotType(vehicleType);
        
        if(spotType == null) {
            System.out.println("Unsupported Vehicle Type provided!!!!");
            return null;
        }
        
        for(ParkingSpot spot: allSlots) {
            
            if(spot.getSpotType() == spotType && !occupiedSlots.contains(spot.getId())) {
                return spot;
            }
        }
        return null;
    }
    
    private SpotType getSpotType(VehicleType vehicleType) {
        if(vehicleType == VehicleType.SMALL) return SpotType.SMALL;
        if(vehicleType == VehicleType.MEDIUM) return SpotType.MEDIUM;
        if(vehicleType == VehicleType.LARGE) return SpotType.LARGE;
        return null;
        
    }
    
}