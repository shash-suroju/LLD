
import java.time.LocalDate;
import java.util.*;
public class BookingService {

    private List<Room> allRooms;
    public BookingService () {
        allRooms = new ArrayList<>();

        Room room = new Room("1", "abcd");
        allRooms.add(room);
    }
    
    public Room findRoom(User user, LocalDate startDate, LocalDate endDate, Long startTime, Long endTime) {
        Room targetRoom = null;

        List<Slot> requiredSlots = new ArrayList<>();
        LocalDate date = startDate;
        while(!date.isAfter(endDate)) {
            String id = UUID.randomUUID().toString();
            requiredSlots.add(new Slot(id, startTime, endTime, date));
            date = date.plusDays(1);
        }

        for(Room room: allRooms) {
            //check if this room is available for booking
            if(roomAvailable(room, requiredSlots)) {
                targetRoom = room;
                break;
            }
        }

        if(targetRoom != null) {
            TreeSet<Slot> targetRoomSlots = targetRoom.getSlots();
            for(Slot slot: requiredSlots) {
                targetRoomSlots.add(slot);
            }
            System.out.println(targetRoomSlots.size());
        }

        return targetRoom;
    }
    // per room -> O(k * logN)


    private boolean roomAvailable(Room room, List<Slot> requiredSlots) {
        TreeSet<Slot> roomSlots = room.getSlots();
        // check all the booked slots in the room with the required slots -> if any 1 collides/clashes return false;

        for(Slot slot: requiredSlots) {
            Slot floor = roomSlots.floor(slot); // floor <= slot
            Slot ceiling = roomSlots.ceiling(slot); // ceiling >= slot

            if(floor != null && floor.getEndTime() > slot.getStartTime()) {
                return false;
            }

            if(ceiling != null && slot.getEndTime() > ceiling.getStartTime()) {
                return false;
            }
        }

        return true;
    }
}
