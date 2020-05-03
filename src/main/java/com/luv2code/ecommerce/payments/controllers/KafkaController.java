package com.luv2code.ecommerce.payments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.payments.services.KafkaProducer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
	@Autowired
	private final KafkaProducer producer;

	@Autowired
	public KafkaController(KafkaProducer producer) {
		this.producer = producer;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producer.sendMessage(message);
	}
}