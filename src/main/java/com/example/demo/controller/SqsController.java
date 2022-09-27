package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SqsController {

	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

	@Value("${cloud.aws.end-point}")
	private String endpointUrl;

	@GetMapping("/put/{message}")
	public void putMessageToQueue(@PathVariable("message") String message) {
		queueMessagingTemplate.send(endpointUrl, MessageBuilder.withPayload(message).build());
	}

	@SqsListener("sqs-queue")
	public void readMessagefromQueue(String message) {
		log.info("SQS message {} ", message);
	}

}
