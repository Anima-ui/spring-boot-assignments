package com.task.discount.service;

import com.task.discount.domain.dto.OrderDTO;
import com.task.discount.domain.model.Customer;
import com.task.discount.domain.model.Order;
import com.task.discount.mapper.OrderMapper;
import com.task.discount.repository.CustomerRepository;
import com.task.discount.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final OrderMapper orderMapper;

    @Autowired
    OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        orderMapper = OrderMapper.INSTANCE;
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO, Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Order order = orderMapper.orderDTOToOrder(orderDTO);
        order.setCustomer(customer);
        order.setTotalPrice(customer.getStatus().applyDiscountPercent(order.getTotalPrice()));

        customer.setOrdersCount(customer.getOrdersCount() + 1);
        customerService.updateCustomerStatus(customer);
        customerRepository.save(customer);

        return orderMapper.orderToOrderDTO(orderRepository.save(order));
    }
}
