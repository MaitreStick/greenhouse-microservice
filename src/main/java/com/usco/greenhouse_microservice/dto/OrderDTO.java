package com.usco.greenhouse_microservice.dto;

import com.usco.greenhouse_microservice.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private List<OrderItem> orderItems;
}