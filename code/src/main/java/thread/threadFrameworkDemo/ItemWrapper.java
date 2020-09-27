package thread.threadFrameworkDemo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ItemWrapper<T> implements Delayed {

    private final long activeTime;
    private final T data;

    public ItemWrapper(long expirationTime, T data) {
        this.activeTime = expirationTime * 1000 + System.currentTimeMillis();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }


    public long getDelay(TimeUnit unit) {
        return unit.convert(this.activeTime
                - System.currentTimeMillis(), unit);
    }

    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.MILLISECONDS)
                - o.getDelay(TimeUnit.MILLISECONDS));
        if (d == 0) {
            return 0;
        } else {
            if (d < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}