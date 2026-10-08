import java.time.LocalDate;
public class Main {
    public static void main(String [] args) {

        BookingService bookingService = new BookingService();
        User user = new User("1", "john");

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2026, 10, 8);
        Room room = bookingService.findRoom(user, startDate, endDate, 1000L, 1130L);

        System.out.println(room.toString());

        Room room1 = bookingService.findRoom(user, startDate, endDate, 1030L, 1130L);

        if(room1 == null) System.out.println("no room found");
        else System.out.println(room1.toString());
    }
}