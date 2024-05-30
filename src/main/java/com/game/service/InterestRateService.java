package com.game.service;

import java.util.Random;

public class InterestRateService extends Thread {
    private volatile int Interest;
    private final Object lock;

    public InterestRateService(int interest, Object lock) {
        this.Interest = interest;
        this.lock = lock;
    }

    public int getInterest() {
        return Interest;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(!isInterrupted()) {
            try {
                Thread.sleep(10000);
                synchronized (lock) {
                    this.Interest = random.nextInt((50 - 10) + 1) + 10;
                    System.out.println(" -- -- -- -- -- -- --- -- -- -- -- -- --");
                    System.out.println("금리 변동 " + this.Interest);
                    System.out.println(" -- -- -- -- -- -- --- -- -- -- -- -- --");
                    System.out.println();
                    lock.notify();
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
