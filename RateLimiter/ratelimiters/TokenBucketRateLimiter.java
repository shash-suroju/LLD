package ratelimiters;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import model.TokenBucket;

public class TokenBucketRateLimiter implements RateLimiter {

    private final Map<String, TokenBucket> buckets;
    private final int MAX_TOKENS = 10;
    private final int REFILL_RATE = 10; // tokens per minute

    public TokenBucketRateLimiter () {
        buckets = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest (String userId) {
        long now = System.currentTimeMillis();


        TokenBucket bucket = buckets.computeIfAbsent(userId, id -> new TokenBucket(MAX_TOKENS, now));

        synchronized (bucket) {
            refillBucket(bucket, now);

            // System.out.println("tokens -> " + bucket.getTokens());
            // refill the bucket 
            if(bucket.getTokens()> 0) {
                bucket.setTokens(bucket.getTokens()-1);
                bucket.setLastRefillTime(now);
                return true;
            } else {
                return false;
            }
        }
        
    }

    private void refillBucket (TokenBucket bucket, long now) {
        long timeElapsed = now - bucket.getLastRefillTime();

        int tokenstoAdd = (int) (timeElapsed / 60000) * REFILL_RATE; // 60000 ms in a minute

        bucket.setTokens(Math.min(bucket.getTokens() + tokenstoAdd, MAX_TOKENS));   
    }
}