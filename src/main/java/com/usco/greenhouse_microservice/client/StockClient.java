package com.usco.greenhouse_microservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "greenhouse-microservice")
public interface StockClient {


    @RequestMapping("/api/stock/{code}")
    boolean stockAvailable(@PathVariable String code);
}