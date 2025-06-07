package com.task.discount.domain.dto;

import com.task.discount.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private List<OrderDTO> orders = new ArrayList<>();
}
