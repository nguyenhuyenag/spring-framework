package com.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandSMSResponse implements Serializable {

	private static final long serialVersionUID = -4741848214153729778L;
	
	private boolean status; 		// Lỗi hoặc thành công
	private String sentId; 			// ID tin nhắn
	private String phonenumber; 	// Số điện thoại đã format
	private String message; 		// Nội dung tin nhắn
	private String description; 	// Mô tả lỗi

}
