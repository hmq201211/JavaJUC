package thread.delayedQueueDemo;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 10:43
 **/
@Data
public class OrderDelayed implements Delayed {
    private long activeTime;
    private Order order;

    public OrderDelayed(long expiration, Order order) {
        this.order = order;
        this.activeTime = expiration * 1000 + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(activeTime - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return diff == 0 ? 0 : (diff < 0) ? -1 : 1;
    }
}
