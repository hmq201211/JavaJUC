package thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo1 {
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("extend thread");
        }
    }

    private static class MyThread2 implements Runnable {

        public void run() {
            System.out.println("implements runnable");
        }
    }

    private static class MyThread3 implements Callable<String> {

        public String call() throws Exception {
            return "implements callable";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // extends Thread
        new MyThread1().start();
        // implements runnable
        new Thread(new MyThread2()).start();
        // implements callable feed it to FutureTask and feed it to Thread
        FutureTask<String> task = new FutureTask<String>(new MyThread3());
        new Thread(task).start();
        System.out.println(task.get());
    }
}
