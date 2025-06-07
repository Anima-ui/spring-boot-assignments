package controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.discount.DiscountApplication;
import com.task.discount.controller.CustomerController;
import com.task.discount.domain.dto.CustomerDTO;
import com.task.discount.domain.enums.CustomerStatus;
import com.task.discount.domain.model.Customer;
import com.task.discount.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = DiscountApplication.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDTO inputDTO = CustomerDTO.builder()
                .firstName("Alex")
                .lastName("Smith")
                .orders(Collections.emptyList())
                .build();

        Customer createdCustomer = Customer.builder()
                .firstName("Alex")
                .lastName("Smith")
                .ordersCount(0)
                .orders(Collections.emptyList())
                .status(CustomerStatus.REGULAR)
                .build();

        Mockito.when(customerService.createCustomer(Mockito.any(CustomerDTO.class))).thenReturn(createdCustomer);

        String inputJson = objectMapper.writeValueAsString(inputDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Alex"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }
}
