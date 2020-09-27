package thread.tools;

import java.sql.PseudoColumnUsage;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-03 11:12
 **/
public class CyclicBarrierDemo {
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("check the sync point, business thread starts");
        }
    });

    private static class SyncRunner implements Runnable {

        @Override
        public void run() {
            try {
                Random random = new Random();
                if (random.nextBoolean()) {
                    int i = random.nextInt(3000);
                    System.out.println(Thread.currentThread().getId() + "sleeps" + i);
                    Thread.sleep(i);
                }
                Thread.sleep(1000);
                CYCLIC_BARRIER.await();
                System.out.println(Thread.currentThread().getId() + " do its job");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new SyncRunner()).start();
        }
    }
}
