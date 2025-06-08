package com.task.discount.controller;

import com.task.discount.domain.dto.CustomerDTO;
import com.task.discount.domain.model.Customer;
import com.task.discount.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Tag(name = "Customer Management", description = "APIs for managing customers")
    @Operation(summary = "Create a customer", description = "Creates a customer and adds it to the db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                        description = "Customer was created successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = CustomerDTO.class)
                        ))
    })
    @PostMapping(path = "/create")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
