package com.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageEnums {

	MSG_0(0, "Invalid username or password"), //
	MSG_1(1, "Invalid brandname"), //
	MSG_2(2, "Invalid phonenumber"), //
	MSG_3(3, "Brandname chưa khai báo"), //
	MSG_4(4, "Partner chưa khai báo"), //
	MSG_5(5, "Template chưa khai báo"), //
	MSG_6(6, "Login telco system fail"), //
	MSG_7(7, "Error sending sms to telco"), //
	MSG_8(8, "Tin nhắn spam, mỗi số điện thoại nhận tối đa 2 lần cho cũng một nội dung trên một ngày, và 50 lần cho nội dung khác nhau trên một ngày"), //
	MSG_100(100, "Database error");

	private int id;
	private String description;

}
