package thread.readWriteDemo;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-04 16:31
 **/
public class UseSynchronizedImpl implements GoodService {
    private GoodInfo goodInfo;

    public UseSynchronizedImpl(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    @Override
    public synchronized GoodInfo getNumber() {
        try {
            Thread.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return this.goodInfo;
    }


    @Override
    public synchronized void setNumber(int count) {
        try {
            Thread.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.goodInfo.sell(count);
    }
}
