package com.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandSMSRequest implements Serializable {

	private static final long serialVersionUID = -6116106803620083340L;

	private int loaitin = 1;
	private String key;
	private String message;
	private String phonenumber;
	// private String username;
	// private String password;
	// private String brandname;

}
