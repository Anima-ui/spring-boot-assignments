package com.task.discount.service;

import com.task.discount.domain.dto.CustomerDTO;
import com.task.discount.domain.enums.CustomerStatus;
import com.task.discount.domain.model.Customer;
import com.task.discount.mapper.CustomerMapper;
import com.task.discount.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = CustomerMapper.INSTANCE;
    }

    public void createCustomer(CustomerDTO customer) {
        customerRepository.save(customerMapper.customerDTOToCustomer(customer));
    }

    public void updateCustomerStatus(Customer customer) {
        int count = customer.getOrdersCount();
        CustomerStatus currentStatus = customer.getStatus();

        if (count >= 20 && currentStatus != CustomerStatus.PLATINUM) {
            customer.setStatus(CustomerStatus.PLATINUM);
        } else if (count >= 10 && count < 20 && currentStatus != CustomerStatus.GOLD) {
            customer.setStatus(CustomerStatus.GOLD);
        }
    }
}
