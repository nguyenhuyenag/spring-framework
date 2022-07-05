package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.request.BrandSMSRequest;
import com.response.BrandSMSResponse;
import com.service.SMSService;

@Controller
@RequestMapping("api")
public class SMSController {

	@Autowired
	private SMSService smsService;

	@PostMapping("send-brand-sms")
	private ResponseEntity<?> sendBrandSMS(@RequestBody BrandSMSRequest request) {
		BrandSMSResponse response = smsService.sendBrandSMS(request);
		return ResponseEntity.ok(response);
	}

}
