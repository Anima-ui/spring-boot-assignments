package controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.discount.DiscountApplication;
import com.task.discount.controller.OrderController;
import com.task.discount.domain.dto.OrderDTO;
import com.task.discount.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = DiscountApplication.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createOrderTest() throws Exception {
        Integer customerId = 1;

        OrderDTO inputDTO = OrderDTO.builder()
                .totalPrice(BigDecimal.valueOf(120))
                .build();

        OrderDTO savedDTO = OrderDTO.builder()
                .totalPrice(BigDecimal.valueOf(108))
                .build();

        Mockito.when(orderService.createOrder(Mockito.any(OrderDTO.class), Mockito.eq(customerId)))
                .thenReturn(savedDTO);

        String orderJson = objectMapper.writeValueAsString(inputDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/order/" + customerId + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalPrice").value(savedDTO.getTotalPrice()));
    }
}
