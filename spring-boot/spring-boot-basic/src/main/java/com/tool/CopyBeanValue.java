package com.tool;

import com.entity.User;

public class CopyBeanValue {

	public static void main(String[] args) {
		User source = new User();
		source.setId(1);
		source.setName("HuyenNV");
		source.setEmail("huyennv@gmail.com");
		User target = new User();
		org.springframework.beans.BeanUtils.copyProperties(source, target);
		// org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
		System.out.println("u1: " + source);
		System.out.println("u2: " + target);
	}

}
