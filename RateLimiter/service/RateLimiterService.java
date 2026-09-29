package service;

import java.util.*;
import ratelimiters.RateLimiter;
import ratelimiters.TokenBucketRateLimiter;
import ratelimiters.FixedWindowRateLimiter;

import model.User;
import model.RateLimiterType;


public class RateLimiterService {

    private final Map<RateLimiterType, RateLimiter> rateLimiters;

    public RateLimiterService () {
        rateLimiters = new HashMap<>();

        rateLimiters.put(RateLimiterType.TOKEN_BUCKET, new TokenBucketRateLimiter());
        rateLimiters.put(RateLimiterType.FIXED_WINDOW, new FixedWindowRateLimiter());
        rateLimiters.put(RateLimiterType.SLIDING_WINDOW_LOG, new SlidingWindowLogRateLimiter());
    }

    public boolean allowRequest (User user) {
        RateLimiterType type = user.getRateLimiterType();

        if (type == null) {
            throw new IllegalArgumentException(
                "No rate limiting type defined"
            );
        }
        

        RateLimiter limiter = rateLimiters.get(type);

        if (limiter == null) {
            throw new IllegalArgumentException(
                "Unsupported rate limiting type"
            );
        }

        return limiter.allowRequest(user.getUserId());
    }

}