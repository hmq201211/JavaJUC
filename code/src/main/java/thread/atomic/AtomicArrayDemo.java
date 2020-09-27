package thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayDemo {
    static int[] values = new int[]{1, 2};
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(values);

    public static void main(String[] args) {
        atomicIntegerArray.getAndSet(0,3);
        System.out.println(values[0]);
        System.out.println(atomicIntegerArray.get(0));
    }
}
