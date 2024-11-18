package com.usco.greenhouse_microservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.usco.greenhouse_microservice.client.StockClient;
import com.usco.greenhouse_microservice.dto.OrderDTO;
import com.usco.greenhouse_microservice.entity.Order;
import com.usco.greenhouse_microservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/warehouse")
public class GreenhouseController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDTO orderDTO) {

        boolean inStock = orderDTO.getOrderItems().stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

        if(inStock) {
            Order order = new Order();

            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderItems(orderDTO.getOrderItems());

            orderRepository.save(order);

            return "Order Saved";
        }

        return "Order Cannot be Saved";
    }

    private String fallbackToStockService() {
        return "Something went wrong, please try after some time";
    }
}
