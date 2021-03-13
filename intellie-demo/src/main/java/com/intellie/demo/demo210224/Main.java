package com.intellie.demo.demo210224;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/24 17:00
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool threadPool = new CustomThreadPool();
        Random random = new Random();
        while (true) {
            String target = random.nextInt(10000)+"";
//            threadPool.executor(target);
            Thread.sleep(5000);
        }


    }
}
