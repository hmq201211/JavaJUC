package thread.semaphore;

import java.util.Random;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-03 16:44
 **/
public class Test {
    private static final MySemaphorePool mySemaphorePool = new MySemaphorePool();

    private static class Worker implements Runnable {

        @Override
        public void run() {
            try {
                long start = System.currentTimeMillis();
                MyConnection myConnection = mySemaphorePool.get();
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getId() + "got connection and took" + (end - start) + "ms");
                Thread.sleep(new Random().nextInt(2000));
                mySemaphorePool.back(myConnection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Worker()).start();
        }
    }
}
