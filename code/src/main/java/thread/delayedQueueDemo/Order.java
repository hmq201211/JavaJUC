package thread.delayedQueueDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: Mingqing Hou
 * @Create: 2020-09-11 10:41
 **/
@Data
@AllArgsConstructor
@ToString
public class Order {
    private Integer no;
    private BigDecimal money;
}
