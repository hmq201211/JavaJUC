package thread.deadLockDemo;

import thread.threadPoolDemo.Task;

public class deadLock {


    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(() -> {
            synchronized (lock1){
                System.out.println(Thread.currentThread().getId()+"got lock 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getId() +"got lock 2");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock2){
                System.out.println(Thread.currentThread().getId()+"got lock 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getId() +"got lock 1");
                }
            }
        }).start();
    }
}
