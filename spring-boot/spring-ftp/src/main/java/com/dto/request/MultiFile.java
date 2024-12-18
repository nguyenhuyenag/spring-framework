package com.dto.request;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultiFile implements Serializable {

	private static final long serialVersionUID = 1L;

	// private String description;
	private MultipartFile[] multipartFile;

}
