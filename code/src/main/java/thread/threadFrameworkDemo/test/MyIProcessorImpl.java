package thread.threadFrameworkDemo.test;

import thread.threadFrameworkDemo.IProcessor;
import thread.threadFrameworkDemo.TaskResult;
import thread.threadFrameworkDemo.TaskResultType;

import java.util.Random;

public class MyIProcessorImpl implements IProcessor<Integer, Integer> {
    @Override
    public TaskResult<Integer> execute(Integer data) {
        Random random = new Random();
        int i = random.nextInt(1000);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i < 500) {
            return new TaskResult<>(TaskResultType.Success, data + i, "success");
        } else if (i < 800) {
            return new TaskResult<>(TaskResultType.Exception, i, "exception");
        } else {
            return new TaskResult<>(TaskResultType.Failure, -1, "failure");
        }
    }
}