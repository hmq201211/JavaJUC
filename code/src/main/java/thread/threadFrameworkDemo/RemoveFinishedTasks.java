package thread.threadFrameworkDemo;

import java.util.concurrent.DelayQueue;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-14 16:42
 **/
public class RemoveFinishedTasks {
    private static final DelayQueue<ItemWrapper<String>> DELAY_QUEUE = new DelayQueue<>();

    private RemoveFinishedTasks() {
    }

    private static class Holder {
        private static final RemoveFinishedTasks removeFinishedTasks = new RemoveFinishedTasks();
    }

    public static RemoveFinishedTasks getInstance() {
        return Holder.removeFinishedTasks;
    }

    public void putDoneJobIntoCache(String job, long expiration) {
        ItemWrapper<String> itemWrapper = new ItemWrapper<>(expiration, job);
        DELAY_QUEUE.offer(itemWrapper);
        System.out.println(job + " 已经结束, 放入缓存, 持续时长为: " + expiration);
    }

    static {
        Thread thread = new Thread(() -> {
            for (; ; ) {
                try {
                    ItemWrapper<String> itemWrapper = DELAY_QUEUE.take();
                    String name = itemWrapper.getData();
                    JobPool.getTasks().remove(name);
                    System.out.println(name + " 过期, 任务已经从工作队列中删除");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("缓存守护线程开启");
    }
}
