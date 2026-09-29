package ratelimiters;

public interface RateLimiter {
    boolean allowRequest(String userId);
}