package thread.singleton;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-14 11:14
 **/
public class InitializationDemandHolderSingleton {
    private static class SingletonHolder {
        private static final InitializationDemandHolderSingleton INITIALIZATION_DEMAND_HOLDER_SINGLETON = new InitializationDemandHolderSingleton();
    }

    private InitializationDemandHolderSingleton() {

    }
    public InitializationDemandHolderSingleton getInitializationDemandHolderSingleton(){
        return SingletonHolder.INITIALIZATION_DEMAND_HOLDER_SINGLETON;
    }
}
