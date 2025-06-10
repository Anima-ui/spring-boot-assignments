package com.task.discount.controller.order;

import com.task.discount.domain.dto.OrderDTO;
import com.task.discount.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderController implements OrderAPI {

    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @Override
    @PostMapping(path = "/{customerId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Integer customerId,
                                                @Valid @RequestBody OrderDTO orderDTO) {

        OrderDTO savedOrder = orderServiceImpl.createOrder(orderDTO, customerId);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}
