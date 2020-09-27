package thread.threadFrameworkDemo;

import thread.threadPoolDemo.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-14 17:09
 **/
public class Job<T,R> {
    private final String name;
    public final int count;
    private final IProcessor<T,R> iProcessor;
    private final AtomicInteger successCount;
    private final AtomicInteger doneCount;
    private final LinkedBlockingDeque<TaskResult<R>> details;
    private final long expiration;

    public Job(String name, int count, IProcessor<T,R> iProcessor, long expiration) {
        this.name = name;
        this.count = count;
        this.iProcessor = iProcessor;
        this.successCount = new AtomicInteger();
        this.doneCount = new AtomicInteger();
        this.details = new LinkedBlockingDeque<>(count);
        this.expiration = expiration;
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getDoneCount() {
        return doneCount.get();
    }

    public int getFailCount() {
        return getDoneCount() - getSuccessCount();
    }

    public IProcessor<T, R> getIProcessor() {
        return iProcessor;
    }

    public String getTotalProcess() {
        return "Success[" + getSuccessCount() + "]/Current[" + getDoneCount()
                + "] Total[" + count + "]";
    }

    public  List<TaskResult<R>> getResults() {
        List<TaskResult<R>> results = new LinkedList<>();
        TaskResult<R> taskResult;
        while ((taskResult = this.details.pollFirst()) != null) {
            results.add(taskResult);
        }
        return results;
    }

    public void addTaskResult(TaskResult<R> taskResult) {
        doneCount.incrementAndGet();
        if (TaskResultType.Success.equals(taskResult.getResultType())) {
            successCount.incrementAndGet();
        }
        details.addLast(taskResult);
        if (getDoneCount() == count) {
            RemoveFinishedTasks.getInstance().putDoneJobIntoCache(name, expiration);
        }
    }

}
