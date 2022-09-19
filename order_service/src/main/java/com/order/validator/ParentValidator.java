package com.order.validator;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.order.ApplicationErrorConstants;
import com.order.Constant;

public interface ParentValidator {

	/***
	 * This method used to check the field value is null or not
	 * 
	 * @param fieldValue
	 * @param fieldName
	 * @return
	 */
	static String isNotNull(Object fieldValue, String fieldName) {
		if (Objects.isNull(fieldValue)) {
			return String.join(" ", fieldName, ApplicationErrorConstants.MANDATORY_FIELD);
		}
		return Constant.SUCCESS;
	}

	/***
	 * This method is used to check the value is blank or not
	 * 
	 * @param fieldValue
	 * @param fieldName
	 * @return
	 */

	static String isNotBlank(String fieldValue, String fieldName) {
		if (StringUtils.isBlank(fieldValue)) {
			return String.join(" ", fieldName, ApplicationErrorConstants.MANDATORY_FIELD);
		}
		return Constant.SUCCESS;
	}

	/***
	 * This method used to check the regex pattern
	 * 
	 * @param regexPattern
	 * @param fieldValue
	 * @param errorMessage
	 * @return
	 */
	static String isPatternValid(String regexPattern, String fieldValue, String errorMessage) {
		if (Pattern.matches(regexPattern, fieldValue)) {
			return Constant.SUCCESS;
		}
		return errorMessage;
	}

}
