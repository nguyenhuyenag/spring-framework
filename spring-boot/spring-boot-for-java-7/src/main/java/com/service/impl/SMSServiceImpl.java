package com.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enums.MessageEnums;
import com.request.BrandSMSRequest;
import com.response.BrandSMSResponse;
import com.service.SMSService;
import com.util.ConfigReader;
import com.util.MessageLogs;

@Service
public class SMSServiceImpl implements SMSService {

	private static final Logger LOG = LoggerFactory.getLogger(SMSServiceImpl.class);

	@Override
	public BrandSMSResponse sendBrandSMS(BrandSMSRequest request) {
		BrandSMSResponse response = new BrandSMSResponse();
		if (!ConfigReader.SMS_KEY.equals(request.getKey())) {
			response.setDescription(MessageLogs.KEY_ERR);
			return response;
		}
		if (StringUtils.isEmpty(request.getPhonenumber())) {
			response.setDescription(MessageLogs.PHONENUMBER_EMPTY);
			return response;
		}
		if (StringUtils.isEmpty(request.getMessage())) {
			response.setDescription(MessageLogs.MSG_EMPTY);
			return response;
		}
		HttpPost httpPost = new HttpPost(ConfigReader.SMS_URL);
		httpPost.setEntity(encodedEntity(request));
		String phonenumber = formatPhonenumber(request.getPhonenumber());
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			LOG.info("Call service for phonenumber: {}", phonenumber);
			HttpResponse execute = client.execute(httpPost);
			int status = execute.getStatusLine().getStatusCode();
			LOG.info("Status: {}", status);
			if (status == 200) {
				String id = IOUtils.toString(execute.getEntity().getContent(), StandardCharsets.UTF_8);
				id = StringUtils.substringBetween(id, "<int xmlns=\"http://tempuri.org/\">", "</int>");
				String desc = getDescription(Integer.parseInt(id));
				if (StringUtils.isEmpty(desc)) {
					response.setStatus(true);
					desc = MessageLogs.SEND_SUCCESS;
				}
				response.setSentId(id);
				response.setDescription(desc);
				response.setMessage(request.getMessage()); 
				response.setPhonenumber(phonenumber);
			}
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private String formatPhonenumber(String phone) {
		if (StringUtils.isNotEmpty(phone)) {
			phone = phone.replaceAll("[\\D.]", "");
			if (phone.startsWith("0")) {
				phone = "84" + phone.substring(1, phone.length());
			}
		}
		return phone;
	}
	
	public static String removeAccents(String text) {
        return StringUtils.stripAccents(text).replaceAll("đ", "d");
    }

	private UrlEncodedFormEntity encodedEntity(BrandSMSRequest req) {
		try {
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("username", ConfigReader.SMS_USERNAME));
			params.add(new BasicNameValuePair("password", ConfigReader.SMS_PASSWORD));
			params.add(new BasicNameValuePair("brandname", ConfigReader.SMS_BRANDNAME));
			params.add(new BasicNameValuePair("loaitin", "1"));
			String phone = formatPhonenumber(req.getPhonenumber());
			params.add(new BasicNameValuePair("phonenumber", phone));
			params.add(new BasicNameValuePair("message", removeAccents(req.getMessage())));
			return new UrlEncodedFormEntity(params);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getDescription(int id) {
		for (MessageEnums e : MessageEnums.values()) {
			if (id == e.getId()) {
				return e.getDescription();
			}
		}
		return "";
	}

//	private String getDescription(int value) {
//		switch (value) {
//			case 0:
//				return MessageLogs.MSG_0;
//			case 1:
//				return MessageLogs.MSG_1;
//			case 2:
//				return MessageLogs.MSG_2;
//			case 3:
//				return MessageLogs.MSG_3;
//			case 4:
//				return MessageLogs.MSG_4;
//			case 5:
//				return MessageLogs.MSG_5;
//			case 6:
//				return MessageLogs.MSG_6;
//			case 7:
//				return MessageLogs.MSG_7;
//			case 8:
//				return MessageLogs.MSG_8;
//			case 100:
//				return MessageLogs.MSG_100;
//			default:
//				return "";
//		}
//	}
	
}
