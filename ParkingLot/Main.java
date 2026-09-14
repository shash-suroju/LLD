/*

ParkingLot

ParkingLotService
- List of allSlots
- Set of Ocuupied Spots
- Map -> strategies
- Map -> issuedTickets;

FareStrategy
BasicFareStrategy
PeakHourFareStrategy


Vehicle
Ticket
ParkingSpot


VehicleType
SpotSize

*/
import models.*;

public class Main{

	public static void main(String[] args) {
		System.out.println("|-------Parking Lot-------|\n");
		
		ParkingLotService parkingLotService = new ParkingLotService();
		
		Vehicle v1 = new Vehicle("v1", VehicleType.SMALL);
		Vehicle v2 = new Vehicle("v2", VehicleType.LARGE);
		Vehicle v3 = new Vehicle("v2", VehicleType.MEDIUM);
		
		Ticket t1 = parkingLotService.parkVehicle(v1);
		Ticket t2 = parkingLotService.parkVehicle(v2);
		
		Ticket t3 = parkingLotService.parkVehicle(v3);
		
		int fare = parkingLotService.exitVehicle(t1);
		
		System.out.println("Fare amount for vehicle: " + v1.getId() + " is " + fare);
		
	}
}



