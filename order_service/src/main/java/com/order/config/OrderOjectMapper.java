package com.order.config;

import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderOjectMapper {

	private OrderOjectMapper() {
	}

	public static String toJson(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy HH:MM:Ss a"));
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception exception) {
			throw new Exception("Error occured while converting object to json");
		}
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (Exception exception) {
			throw new Exception("Error occured while converting object to json");
		}
	}

	public static <T> T fromJson(String jsonString, TypeReference<T> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (Exception exception) {
			throw new Exception("Error occured while converting object to json");
		}
	}

	public static <T> List<T> fromJsonList(String jsonString, Class<T> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} catch (Exception exception) {
			throw new Exception("Error occured while converting object to json");
		}
	}

	public static <T> T fromJsonAcceptEmptyString(String jsonString, Class<T> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (Exception exception) {
			throw new Exception("Error occured while converting object to json");
		}
	}

}
