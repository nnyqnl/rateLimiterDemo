package com.example.rateLimiter.cache;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateCounterContainer {

    private Map<String, RateCounter> counterMap = new HashMap<>();

    public Map<String, RateCounter> getCounterMap() {
        return counterMap;
    }

    public void setCounterMap(Map<String, RateCounter> counterMap) {
        this.counterMap = counterMap;
    }
}
