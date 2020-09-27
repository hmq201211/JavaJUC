package thread.basic;

public class JoinDemo {
    private static class JoinThread implements Runnable {
        private final Thread thread;

        public JoinThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JoinThread(previous), "" + i);
            System.out.println(previous.getName() + "join" + thread.getName());
            thread.start();
            previous = thread;
        }
        Thread.sleep(1000);
        System.out.println("main stopped");
    }
}
