package thread.readWriteDemo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-04 16:25
 **/
@Data
@AllArgsConstructor
public class GoodInfo {
    private final String name;
    private double totalSold;
    private double price;
    private int stored;

    public void sell(int count) {
        this.totalSold += price * count;
        stored -= count;
    }

}
