package controllerTest;

import com.task.discount.DiscountApplication;
import com.task.discount.controller.CustomerController;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = DiscountApplication.class)
public class OrderControllerTest {

}
