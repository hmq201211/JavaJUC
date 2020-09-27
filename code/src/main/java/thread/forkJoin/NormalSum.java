package thread.forkJoin;

public class NormalSum {
    public static void main(String[] args) {
        int sum = 0;
        int[] test = MakeArray.getArray();

        long start = System.currentTimeMillis();
        for (int j : test) {
            sum += j;
        }
        long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end - start);
    }
}
