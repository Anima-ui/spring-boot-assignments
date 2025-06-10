package com.task.discount.service.impl;

import com.task.discount.domain.dto.OrderDTO;
import com.task.discount.domain.model.Customer;
import com.task.discount.domain.model.Order;
import com.task.discount.mapper.OrderMapper;
import com.task.discount.repository.CustomerRepository;
import com.task.discount.repository.OrderRepository;
import com.task.discount.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CustomerServiceImpl customerServiceImpl;
    private final OrderMapper orderMapper;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, CustomerServiceImpl customerServiceImpl) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.customerServiceImpl = customerServiceImpl;
        orderMapper = OrderMapper.INSTANCE;
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO, Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Order order = orderMapper.orderDTOToOrder(orderDTO);
        order.setCustomer(customer);
        order.setTotalPrice(customer.getStatus().applyDiscountPercent(order.getTotalPrice()));

        customer.setOrdersCount(customer.getOrdersCount() + 1);
        customerServiceImpl.updateCustomerStatus(customer);
        customerRepository.save(customer);

        return orderMapper.orderToOrderDTO(orderRepository.save(order));
    }
}
