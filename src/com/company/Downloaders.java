package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread {
    Semaphore sem;
    CountDownLatch cd;
    int numUser;

    public Downloaders(Semaphore sem, CountDownLatch cd, int i) {
        this.sem = sem;
        this.cd = cd;
        this.numUser = i;
    }

    @Override
    public void run() {
        super.run();
        try {
            sem.acquire();
            System.out.println("User " + numUser + " начинает скачивать файл");
            sleep(1000);
            System.out.println("User " + numUser + " скачал файл с сервера");
            cd.countDown();
            sem.release();
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("у User: " + numUser + " проблемы с интернетом");
        }
    }
}
