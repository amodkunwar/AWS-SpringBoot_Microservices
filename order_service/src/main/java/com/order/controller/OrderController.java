package com.order.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.OrderServiceApplication;
import com.order.dto.Order;
import com.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order-service/order")
	public ResponseEntity<String> processOrder(@RequestBody Order order) {
		log.info("Process order has been processed");
		CompletableFuture.runAsync(() -> orderService.processOrder(order));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order has been processed.");
	}

}
