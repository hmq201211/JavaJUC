package thread.waitAndNotifyDemo;

/**
 * @Description: wait and notify test class
 * @Author: Mingqing Hou
 * @Create: 2020-09-02 17:21
 **/
public class Test {
    private static final ExpressUseCondition EXPRESS = new ExpressUseCondition(100, "Beijing");

    private static class checkDistanceWorker extends Thread {
        @Override
        public void run() {
            EXPRESS.checkDistanceChange();
        }
    }

    private static class checkLocationWorker extends Thread {
        @Override
        public void run() {
            EXPRESS.checkLocationChange();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new checkDistanceWorker().start();
        }
        for (int i = 0; i < 3; i++) {
            new checkLocationWorker().start();
        }
        Thread.sleep(1000);
        EXPRESS.setDistance(101);
    }

}
