package com.task.discount.controller.customer;

import com.task.discount.domain.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Customer Management", description = "APIs for managing customers")
public interface CustomerAPI {

    @Operation(summary = "Create a customer", description = "Creates a customer and adds it to the db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Customer was created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)
                    ))
    })
    ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customer);
}
