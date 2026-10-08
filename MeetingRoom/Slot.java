import java.time.LocalDate;
public class Slot implements Comparable<Slot>{
    private String id;
    private Long startTime;
    private Long endTime;

    public Slot(String id, Long startTime, Long endTime, LocalDate date) {
        this.id = id;
        long day = date.toEpochDay();
        this.startTime = day * 2400 + startTime;
        this.endTime = day * 2400 + endTime;
    }

    @Override 
    public int compareTo(Slot other) {
        return Long.compare(this.startTime, other.startTime);
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }
}
