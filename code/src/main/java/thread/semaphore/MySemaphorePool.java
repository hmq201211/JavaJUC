package thread.semaphore;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-03 16:23
 **/
public class MySemaphorePool {
    private static final int SIZE = 10;
    private static final LinkedList<MyConnection> CONNECTIONS = new LinkedList<>();

    static {
        for (int i = 0; i < 10; i++) {
            CONNECTIONS.add(MyConnection.fetchConnection());
        }
    }

    private final Semaphore used, useful;

    public MySemaphorePool() {
        this.useful = new Semaphore(SIZE);
        this.used = new Semaphore(0);
    }

    public MyConnection get() throws InterruptedException {
        useful.acquire();
        MyConnection myConnection;
        synchronized (CONNECTIONS) {
            myConnection = CONNECTIONS.removeFirst();
        }
        used.release();
        return myConnection;
    }

    public void back(MyConnection myConnection) throws InterruptedException {
        if (myConnection != null) {
            used.acquire();
            synchronized (CONNECTIONS) {
                CONNECTIONS.addLast(myConnection);
            }
            useful.release();
        }
    }
}
