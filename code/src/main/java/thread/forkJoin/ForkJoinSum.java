package thread.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;

public class ForkJoinSum extends RecursiveTask<Integer> {
    private static final int THRESHOLD = MakeArray.SIZE / 10;
    private int[] array;
    private int start;
    private int end;

    public ForkJoinSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start < THRESHOLD) {
            int sum = 0;
            for (int i : array) {
                sum += i;
            }
            return sum;
        } else {
            int mid = (end + start) / 2;
            ForkJoinSum left = new ForkJoinSum(array, start, mid);
            ForkJoinSum right = new ForkJoinSum(array, mid + 1, end);
            invokeAll(left, right);
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinSum forkJoinSum = new ForkJoinSum(MakeArray.getArray(), 0, MakeArray.SIZE - 1);
        long start = System.currentTimeMillis();
        forkJoinPool.invoke(forkJoinSum);
        long end = System.currentTimeMillis();
        Integer join = forkJoinSum.join();
        System.out.println(join);
        System.out.println(end - start);

    }
}
