package thread.threadFrameworkDemo;

import thread.threadPoolDemo.Task;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-14 17:08
 **/
public class JobPool {
    private static final ConcurrentHashMap<String, Job<?, ?>> TASKS = new ConcurrentHashMap<>();
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(PROCESSORS, PROCESSORS, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5000));


    public static ConcurrentHashMap<String, Job<?, ?>> getTasks() {
        return TASKS;
    }

    private static class Holder {
        public static final JobPool jobPool = new JobPool();
    }

    public static JobPool getJobPool() {
        return Holder.jobPool;
    }

    private static class TaskWrapper<T, R> implements Runnable {
        Job<T, R> job;
        T data;

        public TaskWrapper(Job<T, R> job, T data) {
            this.job = job;
            this.data = data;
        }

        @Override
        public void run() {
            IProcessor<T, R> iProcessor = job.getIProcessor();
            TaskResult<R> execute = iProcessor.execute(data);
            job.addTaskResult(execute);
        }
    }

    public <T, R> void register(String name, int count, IProcessor<T, R> iProcessor, long expiration) {
        Job<T, R> job = new Job<>(name, count, iProcessor, expiration);
        if (TASKS.putIfAbsent(name, job) != null) {
            throw new RuntimeException("已经注册");
        }
    }

    @SuppressWarnings("unchecked")
    private <T, R> Job<T, R> getJob(String name) {
        Job<T, R> job = (Job<T, R>) TASKS.get(name);
        if (job != null)
            return job;
        else
            throw new RuntimeException("非法任务");
    }

    public <T, R> void putJob(String name, T data) {
        Job<T, R> job = getJob(name);
        TaskWrapper<T, R> taskWrapper = new TaskWrapper<>(job, data);
        EXECUTOR_SERVICE.execute(taskWrapper);
    }

    public <T, R> String getTotalProcess(String name) {
        Job<T, R> job = getJob(name);
        if (job.getDoneCount() == job.count)
            EXECUTOR_SERVICE.shutdown();
        return job.getTotalProcess();
    }

    public <T, R> List<TaskResult<R>> getTaskDetail(String name) {
        Job<T, R> job = getJob(name);
        return job.getResults();
    }

}
