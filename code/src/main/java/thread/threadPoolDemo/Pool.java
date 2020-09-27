package thread.threadPoolDemo;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 14:36
 **/
public class Pool {
    @Override
    public String toString() {
        return "Pool{" +
                "tasks=" + tasks.size() +
                ", workers=" + workers.length +
                '}';
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            Runnable r;
            while (!interrupted()) {
                try {
                    r = tasks.take();
                    r.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void tryInterrupt() {
            interrupt();
        }
    }

    private static final int DEFAULT_WORKER_SIZE = 2;
    private static final int DEFAULT_TASK_SIZE = 20;
    private final BlockingQueue<Task> tasks;
    private final Worker[] workers;

    public Pool() {
        this(DEFAULT_WORKER_SIZE, DEFAULT_TASK_SIZE);
    }

    public Pool(int workers, int tasks) {
        this.tasks = new ArrayBlockingQueue<>(tasks);
        this.workers = new Worker[workers];
        for (int i = 0; i < workers; i++) {
            this.workers[i] = new Worker();
            this.workers[i].start();
        }
    }

    public void execute(Task task) {
        try {
            this.tasks.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        for (int i = 0; i < workers.length; i++) {
            workers[i].tryInterrupt();
            workers[i] = null;
        }
        this.tasks.clear();
    }
}
