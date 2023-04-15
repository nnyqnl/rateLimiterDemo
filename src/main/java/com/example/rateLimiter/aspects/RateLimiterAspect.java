package com.example.rateLimiter.aspects;

import com.example.rateLimiter.annotation.RateLimiter;
import com.example.rateLimiter.cache.RateCounter;
import com.example.rateLimiter.cache.RateCounterContainer;
import com.example.rateLimiter.exception.TooManyRequestsException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流处理
 *
 * @author ruoyi
 */
@Aspect
@Component
public class RateLimiterAspect {


    @Autowired
    private RateCounterContainer container;

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
        int time = rateLimiter.time();
        int limitCount = rateLimiter.limitCount();

        String combineKey = getCombineKey(rateLimiter, point);
        Map<String, RateCounter> counterMap = container.getCounterMap();

        RateCounter rateCounter;
        synchronized (this) {
            counterMap.putIfAbsent(combineKey, new RateCounter());
            rateCounter = counterMap.get(combineKey);
            if (rateCounter.getStartTime() == null) {
                rateCounter.setStartTime(Instant.now());
            }
            if (rateCounter.getStartTime().plusSeconds(time).isBefore(Instant.now())) {
                // 过期了
                rateCounter.setCount(new AtomicInteger(0));
                rateCounter.setStartTime(Instant.now());
            }
        }

        int i = rateCounter.getCount().incrementAndGet();

        if (i > limitCount) {
            throw new TooManyRequestsException("Too Many Requests");
        }


    }

    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }
}
