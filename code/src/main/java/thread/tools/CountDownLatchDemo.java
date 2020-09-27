package thread.tools;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: CountDownLatch 闭锁demo
 * @Author: Mingqing Hou
 * @Create: 2020-09-03 10:25
 **/
public class CountDownLatchDemo {
    private static final CountDownLatch countDownLatch = new CountDownLatch(6);

    private static class initThread implements Runnable {

        @Override
        public void run() {
            countDownLatch.countDown();
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getId() + "-" + i);
            }
        }
    }

    private static class businessThread implements Runnable {

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getId() + "-" + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(
                () -> {
                    countDownLatch.countDown();
                    System.out.println("step 1");
                    countDownLatch.countDown();
                    System.out.println("step 2");
                    countDownLatch.countDown();
                    System.out.println("step 3");
                }
        ).start();
        new Thread(new businessThread()).start();
        for (int i = 0; i < 3; i++) {
            new Thread(new initThread()).start();
        }
        countDownLatch.await();
        System.out.println("finished");
    }
}
