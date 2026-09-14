package strategy;

import models.Ticket;

public interface FareStrategy {
    
    public int calculateFare(Ticket ticket);
}