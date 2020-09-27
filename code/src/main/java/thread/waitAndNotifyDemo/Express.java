package thread.waitAndNotifyDemo;

/**
 * @Description: 快递实体类
 * @Author: Mingqing Hou
 * @Create: 2020-09-02 17:11
 **/
public class Express {
    private static final String FROM = "Beijing";
    private int distance;
    private String location;
    public Express(int distance, String location){
        this.distance = distance;
        this.location = location;
    }
    public synchronized void setDistance(int distance) {
        this.distance = distance;
        notifyAll();
    }

    public synchronized void setLocation(String location) {
        this.location = location;
        notifyAll();
    }

    public synchronized void checkDistanceChange() {
        while (this.distance <= 100) {
            try {
                wait();
                System.out.println(Thread.currentThread().getId() + "被唤醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getId() + "到了" + this.distance + "处");
    }

    public synchronized void checkLocationChange() {
        while (FROM.equals(this.location)) {
            try {
                wait();
                System.out.println(Thread.currentThread().getId() + "被唤醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getId() + "到了" + this.location);
    }
}
