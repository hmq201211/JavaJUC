package thread.threadPoolDemo;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 15:06
 **/
public class Task implements Runnable {
    private final String name;
    private final Integer sleepTime;

    public Task(String name, Integer sleepTime) {
        this.name = name;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(this + " 开始");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this + " 完成");
    }

    @Override
    public String toString() {
        return "Task{"
                + Thread.currentThread().getId()
                + "|"
                + this.name
                + "}";
    }
}
