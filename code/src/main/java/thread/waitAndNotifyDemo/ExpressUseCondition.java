package thread.waitAndNotifyDemo;

import com.sun.source.tree.NewArrayTree;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-04 17:22
 **/
public class ExpressUseCondition {
    private static final String FROM = "Beijing";
    private int distance;
    private String location;
    private final Lock lock = new ReentrantLock();
    private final Condition distanceCondition = lock.newCondition();
    private final Condition locationCondition = lock.newCondition();

    public ExpressUseCondition(int distance, String location) {
        this.distance = distance;
        this.location = location;
    }

    public void setDistance(int distance) {
        lock.lock();
        try {
            this.distance = distance;
            distanceCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void setLocation(String location) {
        lock.lock();
        try {
            this.location = location;
            locationCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void checkDistanceChange() {
        lock.lock();
        try {
            while (this.distance <= 100) {
                try {
                    distanceCondition.await();
                    System.out.println(Thread.currentThread().getId() + "被唤醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getId() + "到了" + this.distance + "处");
    }

    public void checkLocationChange() {
        lock.lock();
        try {
            while (FROM.equals(this.location)) {
                try {
                    locationCondition.await();
                    System.out.println(Thread.currentThread().getId() + "被唤醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getId() + "到了" + this.location);
    }
}
