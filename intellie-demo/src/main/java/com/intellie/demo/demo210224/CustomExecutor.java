package com.intellie.demo.demo210224;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/24 17:17
 */
public class CustomExecutor implements Runnable {

    Logger logger = LoggerFactory.getLogger(CustomExecutor.class);

    private String name;//当前执行器的名称
    private boolean isRunning = true;//执行器是否在运行中
    private boolean isExecutor = false;//是否在执行任务中
    private long lastExecuteTime = 0;//上一次执行任务结束的时间
    private long aliveTime = 10000;//执行器的空闲存活时长

    private String targetName;


    public CustomExecutor(String name, long aliveTime) {
        this.name = name;
        this.aliveTime = aliveTime;
    }

    public String getName() {
        return this.name;
    }


    public synchronized void setTask(String targetName) {
        this.targetName = targetName;
    }

    public synchronized boolean isDestroy() {
        return !this.isRunning;
    }

    public synchronized boolean isExecutor() {
        return !this.isExecutor;
    }

    @Override
    public void run() {
        while (isRunning) {
            isExecutor = true;

            long startTime = System.currentTimeMillis();
            if (lastExecuteTime != 0 && (startTime - lastExecuteTime) >= aliveTime) {
                logger.debug("执行器{}已销毁",name);
                isRunning = false;
            }

            if (!isRunning)
                continue;
            logger.debug("当前线程{}", Thread.currentThread().getName());
            logger.debug("{}:开始执行任务 => {}", name, targetName);

            try {
                Thread.sleep(Long.parseLong(targetName));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.isRunning = false;
            logger.debug("{}:{}执行完毕，耗时{}ms,当前状态:{}", name, targetName,
                    System.currentTimeMillis() - startTime, this.isRunning);
        }
    }
}
