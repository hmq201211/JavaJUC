package thread.delayedQueueDemo;

import java.util.concurrent.DelayQueue;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 10:56
 **/
public class TakeWorker implements Runnable {
    private final DelayQueue<OrderDelayed> delays;

    public TakeWorker(DelayQueue<OrderDelayed> delays) {
        this.delays = delays;
    }

    @Override
    public void run() {
        while (true) {
            try {
                OrderDelayed take = delays.take();
                Order order = take.getOrder();
                System.out.println(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
