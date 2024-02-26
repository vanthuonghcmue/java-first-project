package com.example.demo.MuntipleThreadTest.StartThreads_1;

// Create thread by extend Thread class
class Runner extends Thread{
    public void run(){
        for (int i=0; i<5; i++) {
            System.out.println("hello " + i + " thread: " + Thread.currentThread().getName());
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class ApplicationExtends {
    public static void main(String[] args) {
        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();
    }
}