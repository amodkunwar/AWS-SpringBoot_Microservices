package com.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	private String clientId;
	
	private String orderId;
	
	private Customer customer;
	
	private List<OrderItem> orderItems;
	
}
