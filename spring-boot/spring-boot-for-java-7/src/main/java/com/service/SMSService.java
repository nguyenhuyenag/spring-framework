package com.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.request.BrandSMSRequest;
import com.response.BrandSMSResponse;

public interface SMSService {
	
	BrandSMSResponse sendBrandSMS(@RequestBody BrandSMSRequest request);
	
}
