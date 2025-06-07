package com.task.discount.service;

import com.task.discount.domain.dto.CustomerDTO;
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

    public Customer createCustomer(CustomerDTO customer) {
        return customerRepository.save(customerMapper.customerDTOToCustomer(customer));
    }
}
