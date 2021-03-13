package com.intellie.demo.demo210224;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/25 9:33
 */
public class TaskNum {
        private int num;

        public synchronized int getNum() {
            return num++;
        }
}
