package ratelimiters;

public class FixedWindowRateLimiter implements RateLimiter {

    @Override
    public boolean allowRequest (String userId) {
        return true;
    }
}