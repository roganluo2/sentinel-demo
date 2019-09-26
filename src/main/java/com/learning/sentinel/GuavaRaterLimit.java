package com.learning.sentinel;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GuavaRaterLimit {

    private static RateLimiter rateLimiter = RateLimiter.create(10);


    public static void main(String[] args) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i = 0;i< 100;i++)
        {
            new Thread(() ->{
                try {
                    countDownLatch.await();
                    TimeUnit.MICROSECONDS.sleep(1000);
                invokeMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }).start();
        }
        countDownLatch.countDown();
        System.in.read();

    }

    private static void invokeMethod() {

        if(rateLimiter.tryAcquire())
        {
            System.out.println("执行成功");
        }
        else
        {
            System.out.println("限流了");
        }
    }

}
