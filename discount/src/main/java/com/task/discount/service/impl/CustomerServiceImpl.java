package com.task.discount.service.impl;

import com.task.discount.domain.dto.CustomerDTO;
import com.task.discount.domain.enums.CustomerStatus;
import com.task.discount.domain.model.Customer;
import com.task.discount.mapper.CustomerMapper;
import com.task.discount.repository.CustomerRepository;
import com.task.discount.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = CustomerMapper.INSTANCE;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        Customer savedCustomer = customerRepository.save(customerMapper.customerDTOToCustomer(customer));
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
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
