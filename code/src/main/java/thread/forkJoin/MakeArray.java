package thread.forkJoin;

import java.util.Random;

public class MakeArray {
    public static final int SIZE = 500000000;

    public static int[] getArray() {
        int[] toReturn = new int[SIZE];
        Random random = new Random(SIZE);
        for (int i = 0; i < SIZE; i++) {
            toReturn[i] = random.nextInt(SIZE * 3);
        }
        return toReturn;
    }
}
