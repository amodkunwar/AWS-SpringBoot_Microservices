package com.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor
public class Notification {

	@JsonProperty("subject")
	@NonNull
	private final String subject;

	@JsonProperty("message")
	private final String message;

}
