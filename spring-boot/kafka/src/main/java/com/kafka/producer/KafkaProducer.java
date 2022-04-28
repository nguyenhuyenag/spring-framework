package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.MessageService;

@Controller
// @RequestMapping("kafka")
public class KafkaProducer {

	@Autowired
	private MessageService messageService;

	@GetMapping("send-message")
	private ResponseEntity<String> send(@RequestParam(value = "amount", defaultValue = "0", required = false) int amount) {
		messageService.send(amount);
		return ResponseEntity.ok("OK");
	}

}
