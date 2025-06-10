package com.task.discount.service;

import com.task.discount.domain.dto.CustomerDTO;
import com.task.discount.domain.model.Customer;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customer);

    void updateCustomerStatus(Customer customer);
}
