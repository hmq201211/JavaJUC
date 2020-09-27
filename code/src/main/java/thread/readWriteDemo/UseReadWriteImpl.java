package thread.readWriteDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-04 16:43
 **/
public class UseReadWriteImpl implements GoodService {
    private GoodInfo goodInfo;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock read = reentrantReadWriteLock.readLock();
    private final Lock write = reentrantReadWriteLock.writeLock();

    public UseReadWriteImpl(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    @Override
    public GoodInfo getNumber() {
        read.lock();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            read.unlock();
        }
        return this.goodInfo;
    }


    @Override
    public void setNumber(int count) {
        write.lock();
        try {
            Thread.sleep(5);
            this.goodInfo.sell(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            write.unlock();
        }
    }
}
