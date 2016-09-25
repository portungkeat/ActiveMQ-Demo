package com.fudj.test;

/**
 * Created by portk on 2016/9/25 0025.
 */
public class ThreadTest implements Runnable {
    int i = 10;

    @Override
    public void run() {
        for (int l = 0; l < 20; l++) {
            System.out.println(i);
            i--;
        }
    }

    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
    }
}
