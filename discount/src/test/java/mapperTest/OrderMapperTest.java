package mapperTest;

import com.task.discount.domain.dto.OrderDTO;
import com.task.discount.domain.model.Customer;
import com.task.discount.domain.model.Order;
import com.task.discount.mapper.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OrderMapperTest {

    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @Test
    public void orderToOrderDTOTest() {
        Order order = Order.builder()
                .totalPrice(BigDecimal.valueOf(10L))
                .customer(new Customer())
                .build();

        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);

        Assertions.assertNotNull(orderDTO);
        Assertions.assertEquals(order.getTotalPrice(), orderDTO.getTotalPrice());
    }

    @Test
    public void orderDTOToOrderTest() {
        OrderDTO orderDTO = OrderDTO.builder()
                .totalPrice(BigDecimal.valueOf(10L))
                .build();

        Order order = orderMapper.orderDTOToOrder(orderDTO);

        Assertions.assertNotNull(order);
        Assertions.assertEquals(order.getTotalPrice(), orderDTO.getTotalPrice());
    }
}
