import java.util.TreeSet;

public class Room {
    private String id;
    private String name;
    private TreeSet<Slot> slots;

    public Room(String id, String name) {
        this.id = id;
        this.name = name;
        this.slots = new TreeSet<>();
    }

    public TreeSet<Slot> getSlots() {
        return slots;
    }

    @Override 
    public String toString() {
        return "RoomId: " + id + " Name: " + name;
    }
}
