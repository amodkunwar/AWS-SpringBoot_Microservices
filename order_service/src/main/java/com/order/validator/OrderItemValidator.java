package com.order.validator;

import java.util.function.Function;

import com.order.Constant;
import com.order.dto.OrderItem;

public interface OrderItemValidator extends Function<OrderItem, String> {

	static OrderItemValidator isItemIdNotBlank() {
		return item -> ParentValidator.isNotBlank(item.getItemId(), "ItemId");

	}

	static OrderItemValidator isItemNameNotBlank() {
		return item -> ParentValidator.isNotBlank(item.getItemName(), "ItemName");

	}

	static OrderItemValidator isItemQuantityNotNotBlank() {
		return item -> ParentValidator.isNotNull(item.getQuantity(), "ItemQuantity");

	}

	static OrderItemValidator isItemPriceNotNotBlank() {
		return item -> ParentValidator.isNotNull(item.getPrice(), "ItemPrice");

	}

	default OrderItemValidator and(OrderItemValidator other) {
		return item -> {
			String result = this.apply(item);
			return result.equalsIgnoreCase(Constant.SUCCESS) ? other.apply(item) : result;
		};
	}

}
