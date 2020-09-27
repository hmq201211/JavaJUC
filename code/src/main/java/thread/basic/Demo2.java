package thread.basic;

public class Demo2 {
    private static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (!isInterrupted()) {
                System.out.println(name + " is running");
            }
            System.out.println(name + " is interrupted");
        }
    }

    private static class MyThread2 implements Runnable {


        public void run() {
            String name = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(name + " is running");
            }
            System.out.println(name + " is interrupted");
        }
    }
    private static class MyThreadException implements Runnable {


        public void run() {
            String name = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(name + " is running");
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println(name + " is interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread = new MyThread("myThread");
//        myThread.start();
//        Thread.sleep(100);
//        myThread.interrupt();
        Thread name = new Thread(new MyThreadException(), "name");
        name.start();
        Thread.sleep(500);
        name.interrupt();

    }
}
