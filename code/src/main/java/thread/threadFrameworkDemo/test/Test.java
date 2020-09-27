package thread.threadFrameworkDemo.test;

import thread.threadFrameworkDemo.Job;
import thread.threadFrameworkDemo.JobPool;
import thread.threadFrameworkDemo.TaskResult;
import thread.threadPoolDemo.Pool;

import java.util.List;
import java.util.Random;

public class Test {
    private static final String TASK_NAME = "compute";
    private static final int COUNT = 1000;
    private static final JobPool JOB_POOL = JobPool.getJobPool();

    public static void main(String[] args) {

        MyIProcessorImpl myIProcessor = new MyIProcessorImpl();
        JOB_POOL.register(TASK_NAME, COUNT, myIProcessor, 20);
        Random random = new Random();
        for (int i = 0; i < COUNT; i++) {
            JOB_POOL.putJob(TASK_NAME, random.nextInt(100));
        }
        new Thread(() -> {
            int count = 0;
            while (count < 330) {
                String totalProcess = JOB_POOL.getTotalProcess(TASK_NAME);
                List<TaskResult<Integer>> taskDetail = JOB_POOL.getTaskDetail(TASK_NAME);
                for (TaskResult<Integer> result : taskDetail) {
                    System.out.println(result.toString());
                }
                System.out.println(totalProcess);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }).start();
    }
}
