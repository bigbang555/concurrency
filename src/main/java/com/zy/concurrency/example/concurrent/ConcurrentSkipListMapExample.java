package com.zy.concurrency.example.concurrent;

import com.zy.concurrency.annotations.NotThreadSafe;
import com.zy.concurrency.annotations.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Horizon Time: 下午11:11 2018/11/27 Description:
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListMapExample {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;


    private static Map<Integer, Integer> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(finalI);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    public static void update(int i) {
        map.put(i, i);
    }
}
