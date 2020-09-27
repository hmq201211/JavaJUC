package thread.singleton;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-14 11:10
 **/
public class HungrySingleton {
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {

    }

    public HungrySingleton getHungrySingleton() {
        return hungrySingleton;
    }
}
