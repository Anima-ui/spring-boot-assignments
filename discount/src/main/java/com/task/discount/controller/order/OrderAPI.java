package com.task.discount.controller.order;

import com.task.discount.domain.dto.OrderDTO;
import com.task.discount.exceptionHandling.apiError.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Order Management", description = "APIs for managing orders")
public interface OrderAPI {

    @Operation(summary = "Create an order", description = "Creates an order and adds it to the db")
    @Parameter(name = "customerId",
            description = "ID of the customer who is placing the order",
            required = true,
            example = "42")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class)
                    )),
            @ApiResponse(responseCode = "404", description = "User with this customerId was not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)
                    ))
    })
    ResponseEntity<OrderDTO> createOrder(@PathVariable Integer customerId,
                                         @Valid @RequestBody OrderDTO orderDTO);
}
