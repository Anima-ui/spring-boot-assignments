package com.task.discount.controller.order;

import com.task.discount.domain.dto.OrderDTO;
import com.task.discount.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderController implements OrderAPI {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    @PostMapping(path = "/{customerId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Integer customerId,
                                                @Valid @RequestBody OrderDTO orderDTO) {

        OrderDTO savedOrder = orderService.createOrder(orderDTO, customerId);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}
