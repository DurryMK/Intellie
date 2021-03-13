package com.intellie.demo.demo210224;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/24 17:00
 */
public class CustomThreadPool {

    Logger logger = LoggerFactory.getLogger(CustomThreadPool.class);

    private int coreNum;//默认初始大小
    private int maxNum;//最大值
    private long keepAliveTime;//空闲线程的存活时间 单位秒
    private int queueLength;//等待队列的长度

    private ThreadNameBuilder builder;//线程命名规则
    private String rejectHandler;//拒绝策略

    private Queue<Runnable> waitQueue;//等待队列
    private CopyOnWriteArrayList<CustomExecutor> container = new CopyOnWriteArrayList<>();//线程容器

    private AtomicInteger ai;
    private TaskNum taskNum;//任务数量

    private void init() {
        this.coreNum = 10;
        this.maxNum = 15;
        this.keepAliveTime = 60;
        this.queueLength = 10;
        this.waitQueue = new ArrayBlockingQueue<>(this.queueLength);
        this.builder = () -> "custom-pool-num-";
        this.taskNum = new TaskNum();
        this.rejectHandler = "";
        this.ai.set(0);
    }

    public CustomThreadPool() {
        this.init();
    }

    public CustomThreadPool(int coreNum, int maxNum, ThreadNameBuilder builder,
                            long keepAliveTime, int queueLength,String rejectHandler) {
        this.queueLength = queueLength;
        this.init();
        this.coreNum = coreNum;
        this.maxNum = maxNum;
        this.builder = builder;
        this.keepAliveTime = keepAliveTime;
        this.rejectHandler = rejectHandler;
    }

    public CustomThreadPool(int coreNum, int maxNum) {
        this.init();
        this.coreNum = coreNum;
        this.maxNum = maxNum;
    }

    public synchronized void executor(Runnable task) {

    }

}
