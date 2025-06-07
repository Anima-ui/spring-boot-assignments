package mapperTest;

import com.task.discount.domain.dto.CustomerDTO;
import com.task.discount.domain.enums.CustomerStatus;
import com.task.discount.domain.model.Customer;
import com.task.discount.mapper.CustomerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerMapperTest {

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void testCustomerToCustomerDTO(){
        Customer customer = Customer.builder()
                .id(1)
                .firstName("Alex")
                .lastName("Smith")
                .ordersCount(5)
                .status(CustomerStatus.REGULAR)
                .build();

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        Assertions.assertNotNull(customerDTO);
        Assertions.assertEquals(customerDTO.getFirstName(), customer.getFirstName());
        Assertions.assertEquals(customerDTO.getLastName(), customer.getLastName());
    }

    @Test
    public void testCustomerDTOToCustomer(){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("Alex")
                .lastName("Smith")
                .build();

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        Assertions.assertEquals(customer.getLastName(), customerDTO.getLastName());
    }
}
