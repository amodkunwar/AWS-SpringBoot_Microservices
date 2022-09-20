package com.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.notification.model.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NotificationController {

	private static final String TOPIC_ARN = "arn:aws:sns:us-west-2:376418132223:mobile-notificaiton-sns";

	@Autowired
	private AmazonSNSClient amazonSNSClient;

	// URL - http://localhost:10093/addSubscription/myemail@somecompany.com
	@PostMapping(value = "/addSubscription/{email}")
	public ResponseEntity<String> addSubscription(@PathVariable("email") final String email) {
		log.info("Adding new email subscription = {} to the topic.", email);
		final SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, "email", email);
		amazonSNSClient.subscribe(subscribeRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// URL - http://localhost:10093/sendNotification
	// Sample request body -
	// {
	// "subject": "Springboot sns demo email",
	// "message": "Lorem Ipsum is simply dummied text of the printing and
	// typesetting industry."
	// }
	@PostMapping(value = "/sendNotification")
	public ResponseEntity<String> publishMessageToTopic(@RequestBody final Notification notification) {
		log.info("Publishing the notification = {} to the topic.", notification.toString());
		final PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, notification.getMessage(),
				notification.getSubject());
		amazonSNSClient.publish(publishRequest);
		return new ResponseEntity<>("Notification sent successfully!!", HttpStatus.OK);
	}

//	For postman
//	// HTTP POST - Create a new subscription
//	http://localhost:10093/addSubscription/myemail@somecompany.com
//	 
//	// HTTP POST - Send a notification
//	http://localhost:10093/sendNotification

}
