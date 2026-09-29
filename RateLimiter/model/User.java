package model;

public class User {
    private final String userId;
    private final RateLimiterType rateLimiterType;

    public User(String userId, RateLimiterType rateLimiterType) {
        this.userId = userId;
        this.rateLimiterType = rateLimiterType;
    }

    public String getUserId() {
        return userId;
    }

    public RateLimiterType getRateLimiterType() {
        return rateLimiterType;
    }
}