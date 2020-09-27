package thread.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("haha", 1);

    public static void main(String[] args) throws InterruptedException {
        final int initStamp = atomicStampedReference.getStamp();
        final String initValue = atomicStampedReference.getReference();
        System.out.println(Thread.currentThread().getName() + initStamp + initValue);
        Thread right = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + atomicStampedReference.getStamp() + atomicStampedReference.getReference());
                    boolean success = atomicStampedReference.compareAndSet(initValue, "hehe", initStamp, initStamp + 1);
                    System.out.println(success);
                }
        );
        Thread wrong = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + atomicStampedReference.getStamp() + atomicStampedReference.getReference());
                    boolean success = atomicStampedReference.compareAndSet(initValue, "hehe", initStamp, initStamp + 1);
                    System.out.println(success);
                }
        );
        right.start();
        right.join();
        wrong.start();
        wrong.join();
        System.out.println(atomicStampedReference.getStamp()+atomicStampedReference.getReference());
    }
}
