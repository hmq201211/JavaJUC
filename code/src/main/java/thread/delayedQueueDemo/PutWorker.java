package thread.delayedQueueDemo;

import java.math.BigDecimal;
import java.util.concurrent.DelayQueue;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 10:50
 **/
public class PutWorker implements Runnable {
    private final DelayQueue<OrderDelayed> delays;

    public PutWorker(DelayQueue<OrderDelayed> delays) {
        this.delays = delays;
    }

    @Override
    public void run() {
        Order order1 = new Order(1, new BigDecimal("100"));
        Order order2 = new Order(2, new BigDecimal("200"));
        OrderDelayed orderDelayed1 = new OrderDelayed(2, order1);
        OrderDelayed orderDelayed2 = new OrderDelayed(4, order2);
        System.out.println("订单1 2秒后到期");
        delays.offer(orderDelayed1);
        System.out.println("订单2 4秒后到期");
        delays.offer(orderDelayed2);
    }
}
