package thread.delayedQueueDemo;

import java.util.concurrent.DelayQueue;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 11:00
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<OrderDelayed> delays = new DelayQueue<>();
        new Thread(new PutWorker(delays)).start();
        Thread thread = new Thread(new TakeWorker(delays));
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            System.out.println(500 * i);
        }
    }
}
