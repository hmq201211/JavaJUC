package thread.threadPoolDemo;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 15:12
 **/
public class Test {
    public static void main(String[] args) {
        Pool pool = new Pool();
        System.out.println(pool);
        for (int i = 0; i < 5; i++) {
            pool.execute(new Task("" + i, i));
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.destroy();
        System.out.println(pool);
    }
}
