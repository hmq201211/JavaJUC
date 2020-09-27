package thread.singleton;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-14 11:07
 **/
public class DoubleCheck {
    private volatile DoubleCheck doubleCheck;

    private DoubleCheck() {

    }

    public DoubleCheck getDoubleCheck() {
        if (doubleCheck == null) {
            synchronized (DoubleCheck.class) {
                if (doubleCheck == null)
                    doubleCheck = new DoubleCheck();
            }
        }
        return doubleCheck;
    }
}
