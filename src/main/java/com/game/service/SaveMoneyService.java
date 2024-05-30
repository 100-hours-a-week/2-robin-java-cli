package com.game.service;

import java.util.Random;

public class SaveMoneyService extends Thread {
    private final InterestRateService interestRateService;
    private final Object lock;
    private int beforeInterest;

    public SaveMoneyService(InterestRateService interestRateService, Object lock, int beforeInterest) {
        this.interestRateService = interestRateService;
        this.lock = lock;
        this.beforeInterest = beforeInterest;
    }


    @Override
    public synchronized void run() {
        while(!isInterrupted()) {
            synchronized (lock) {
                try {
                    lock.wait();
                    int currentInterest = interestRateService.getInterest();
                    if(beforeInterest < currentInterest) {
                        System.out.println("이자율 상승 (+)"+ (currentInterest - beforeInterest));
                    } else if(beforeInterest > currentInterest) {
                        System.out.println("이자율 하락 (-)"+ (beforeInterest - currentInterest));
                    } else {
                        System.out.println("동결 0");
                    }
                    beforeInterest = currentInterest;
                } catch(InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("저축계좌 이자율 정지");
                    break;
                }
            }
        }
    }
}
