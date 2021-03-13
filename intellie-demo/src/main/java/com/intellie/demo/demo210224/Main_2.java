package com.intellie.demo.demo210224;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/25 9:24
 */
public class Main_2 {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        int count=1;
//        CustomExecutor task ;
//        while(true){
//            task = new CustomExecutor("任务"+count++);
//            task.targetName("10000");
//            executorService.submit(task);
//            Thread.sleep(5000);
//        }
        AtomicInteger integer = new AtomicInteger();
        integer.set(0);
        System.out.println(integer.addAndGet(1));
    }
}
