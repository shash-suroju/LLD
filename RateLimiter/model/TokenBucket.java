package model;

public class TokenBucket {
    private int tokens;
    private long lastRefillTime;

    public TokenBucket (int tokens, long lastRefillTime) {
        this.tokens = tokens;
        this.lastRefillTime = lastRefillTime;
    }

    public int getTokens() {
        return tokens;
    }

    public long getLastRefillTime() {
        return lastRefillTime;
    }

    public void setLastRefillTime(long now) {
        lastRefillTime = now;
    }

    public void setTokens(int t) {
        tokens = t;
    }
}