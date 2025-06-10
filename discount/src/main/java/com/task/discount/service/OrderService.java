package com.task.discount.service;

import com.task.discount.domain.dto.OrderDTO;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO, Integer customerId);

}
