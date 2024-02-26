package com.example.demo.MuntipleThreadTest.StartThreads_1;

public class StartingThreadsTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<5; i++) {
                    System.out.println("hello " + i + " thread: " + Thread.currentThread().getName());
                }
            }

        });

        thread.start();
    }
}

