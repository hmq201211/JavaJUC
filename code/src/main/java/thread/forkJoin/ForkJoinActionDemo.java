package thread.forkJoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @Description: ForkJoinAction 异步用法
 * @Author: Mingqing Hou
 * @Create: 2020-09-03 10:02
 **/
public class ForkJoinActionDemo {
    private static class Worker extends RecursiveAction {
        private final File path;

        public Worker(File path) {
            this.path = path;
        }

        @Override
        protected void compute() {
            File[] files = path.listFiles();
            if (files != null) {
                List<Worker> workers = new ArrayList<>();
                for (File file : files) {
                    if (file.isDirectory()) {
                        workers.add(new Worker(file));
                    } else {
                        if (file.getAbsolutePath().endsWith(".txt")) {
                            System.out.println(file.getAbsolutePath());
                        }
                    }
                }
                if (!workers.isEmpty()) {
                    for (Worker worker : invokeAll(workers)) { // 提交子任务到ForkJoin
                        worker.join(); // 获取子任务结果 阻塞
                    }
                }
            }
        }

        public static void main(String[] args) {
            File file = new File("D://");
            Worker worker = new Worker(file);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            System.out.println("-------------------");
            forkJoinPool.execute(worker);
            System.out.println("*******************");
            worker.join(); // 获取主任务结果 阻塞
            System.out.println("!!!!!!!!!!!!!!!!!!!");
        }
    }
}
