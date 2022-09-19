package com.order.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.order.ApplicationErrorConstants;
import com.order.Constant;
import com.order.dto.Order;
import com.order.dto.OrderItem;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderDtoValidator {

	public String validateOrder(Order order) {
		log.info("Order validation started");
		return validateNotBlankFields(order);
	}

	private String validateNotBlankFields(Order order) {
		log.info("validate blank field");
		String isFieldNotBlank = validateNotBlankOrderDto(order);
		if (!isFieldNotBlank.equalsIgnoreCase(Constant.SUCCESS)) {
			return isFieldNotBlank;
		}

		isFieldNotBlank = validateEmail(order);
		if (!isFieldNotBlank.equalsIgnoreCase(Constant.SUCCESS)) {
			return isFieldNotBlank;
		}

		List<OrderItem> invalidOrderItems = new ArrayList<>();
		List<OrderItem> validOrderItems = new ArrayList<>();

		for (OrderItem orderItem : order.getOrderItems()) {
			isFieldNotBlank = validateNotBlankOrderItems(orderItem, invalidOrderItems);
			isFieldNotBlank = validateOrderItems(orderItem, isFieldNotBlank, validOrderItems, invalidOrderItems);
		}

		if (!isFieldNotBlank.equalsIgnoreCase(Constant.SUCCESS)) {
			return isFieldNotBlank;
		}

		return isFieldNotBlank;
	}

	private String validateOrderItems(OrderItem orderItem, String isFieldNotBlank, List<OrderItem> validOrderItems,
			List<OrderItem> invalidOrderItems) {
		if (isFieldNotBlank.equalsIgnoreCase(Constant.SUCCESS)) {
			orderItem.setItemId(orderItem.getItemId().trim().replaceAll(Constant.ITEM_ID_LEADING_ZERO, ""));
		}
		return isFieldNotBlank;
	}

	private String validateNotBlankOrderItems(OrderItem orderItem, List<OrderItem> invalidOrderItems) {
		String orderItemValidOrInvalid = OrderItemValidator.isItemIdNotBlank()
				.and(OrderItemValidator.isItemNameNotBlank()).and(OrderItemValidator.isItemPriceNotNotBlank())
				.and(OrderItemValidator.isItemQuantityNotNotBlank()).apply(orderItem);

		if (orderItemValidOrInvalid.equalsIgnoreCase(Constant.SUCCESS)) {
			return orderItemValidOrInvalid;
		} else {
			invalidOrderItems.add(orderItem);
			orderItemValidOrInvalid = ApplicationErrorConstants.INVALID_ITEM;
		}

		return orderItemValidOrInvalid;
	}

	private String validateEmail(Order order) {
		String isvalidEmailOrNot = Ordervalidator.isvalidEmailOrNot().apply(order);
		if (!isvalidEmailOrNot.equalsIgnoreCase(Constant.SUCCESS)) {
			return isvalidEmailOrNot;
		}
		return isvalidEmailOrNot;
	}

	private String validateNotBlankOrderDto(Order order) {
		log.info("validate order dto blank field");
		return Ordervalidator.isOrderIdNotBlank().and(Ordervalidator.isCustomerNotNull())
				.and(Ordervalidator.isOrderItemNotNull()).and(Ordervalidator.isvalidEmailOrNot()).apply(order);
	}

}
