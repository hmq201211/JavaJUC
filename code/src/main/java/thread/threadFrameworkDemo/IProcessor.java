package thread.threadFrameworkDemo;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-14 16:34
 **/
public interface IProcessor<T, R> {
    TaskResult<R> execute(T data);
}
