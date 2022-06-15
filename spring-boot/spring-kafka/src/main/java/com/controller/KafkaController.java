package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.KafkaService;

@RestController
public class KafkaController {

	// private static final Logger LOG = LoggerFactory.getLogger(KafkaController.class);

	@Autowired
	private KafkaService kafkaService;
	
	@GetMapping("kafka-consumer")
	private ResponseEntity<?> kafkaConsumer(@RequestParam(required = true, defaultValue = "false") boolean trigger) {
		kafkaService.triggerConsumer(trigger);
		return ResponseEntity.ok("OK");
	}

}
