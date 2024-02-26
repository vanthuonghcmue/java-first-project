package com.example.demo.MuntipleThreadTest.VolatileKeyword_2;

import java.util.Scanner;

class Processor extends Thread{
    private volatile boolean x = true;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            x=!x;
            System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getName() + " x= " + x);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
public class App {
    public static void main(String[] args) {
        Processor pro1 = new Processor();
        pro1.start();

        Processor pro2 = new Processor();
        pro2.start();

    }

}
