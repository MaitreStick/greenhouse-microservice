package com.usco.greenhouse_microservice.repository;



import com.usco.greenhouse_microservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
