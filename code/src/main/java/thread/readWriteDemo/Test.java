package thread.readWriteDemo;

import java.util.Random;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-04 16:49
 **/
public class Test {
    static final int ratio = 10;
    static final int max = 3;

    private static class ReadThread implements Runnable {
        private GoodService goodService;

        public ReadThread(GoodService goodService) {
            this.goodService = goodService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                goodService.getNumber();
            }
            System.out.println(Thread.currentThread().getName() + "读" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    private static class WriteThread implements Runnable {
        private GoodService goodService;

        public WriteThread(GoodService goodService) {
            this.goodService = goodService;

        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goodService.setNumber(random.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName() + "写" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public static void main(String[] args) {
        GoodInfo goodInfo = new GoodInfo("good", 0, 20, 1100000);
        GoodService goodService = new UseReadWriteImpl(goodInfo);
        for (int i = 0; i < max; i++) {
            Thread write = new Thread(new WriteThread(goodService));
            for (int j =0 ; j <ratio;j++){
                new Thread(new ReadThread(goodService)).start();
            }
            write.start();
        }
    }
}
