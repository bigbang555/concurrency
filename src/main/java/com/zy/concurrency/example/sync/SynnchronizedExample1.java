package com.zy.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Horizon
 * Time: 下午4:47 2018/10/21
 * Description:
 */
@Slf4j
public class SynnchronizedExample1 {

    //修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", j,i);
            }
        }
    }

    //修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}", j,i);
        }
    }

    public static void main(String[] args) {
        SynnchronizedExample1 example1 = new SynnchronizedExample1();
        SynnchronizedExample1 example2 = new SynnchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }

}
