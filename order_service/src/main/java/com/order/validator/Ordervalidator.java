package com.order.validator;

import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;

import com.order.ApplicationErrorConstants;
import com.order.Constant;
import com.order.dto.Order;

public interface Ordervalidator extends Function<Order, String> {

	static Ordervalidator isOrderIdNotBlank() {
		return order -> ParentValidator.isNotBlank(order.getOrderId(), Constant.ORDER_ID);
	}

	static Ordervalidator isCustomerNotNull() {
		return order -> {
			String nullCheck = ParentValidator.isNotNull(order, "OrderId");
			if (nullCheck.equalsIgnoreCase(Constant.SUCCESS)) {
				nullCheck = ParentValidator.isNotNull(order.getCustomer(), "Customer");
				if (Objects.nonNull(nullCheck)) {
					return Constant.SUCCESS;
				}
				return String.join(" ", "Order", ApplicationErrorConstants.MANDATORY_FIELD);
			}
			return nullCheck;
		};
	}

	static Ordervalidator isOrderItemNotNull() {
		return order -> {
			String nullCheck = ParentValidator.isNotNull(order, "OrderId");
			if (nullCheck.equalsIgnoreCase(Constant.SUCCESS)) {
				nullCheck = ParentValidator.isNotNull(order.getOrderItems(), "OrderItem");
				if (Objects.nonNull(nullCheck)) {
					return Constant.SUCCESS;
				}
				return String.join(" ", "Order", ApplicationErrorConstants.MANDATORY_FIELD);
			}
			return nullCheck;
		};
	}

	static Ordervalidator isvalidEmailOrNot() {
		return order -> {
			boolean matcher = Pattern.compile(Constant.EMAIL_PATTERN).matcher(order.getCustomer().getEmail()).matches();
			if (matcher) {
				return Constant.SUCCESS;
			}
			return String.format(ApplicationErrorConstants.EMAIL_INVALID, order.getCustomer().getEmail());
		};
	}

	default Ordervalidator and(Ordervalidator other) {
		return order -> {
			String result = this.apply(order);
			return result.equals(Constant.SUCCESS) ? other.apply(order) : result;
		};
	}

}
