package strategy;

import models.Ticket;

public class BasicFareStrategy implements FareStrategy{
    private final int base;
    
    public BasicFareStrategy (int base) {
        this.base = base;
    }
    
    @Override
    public int calculateFare(Ticket ticket) {
        long time = System.currentTimeMillis() - ticket.getEntryTime();
        
        return (int) time*base / 60;
    }
}