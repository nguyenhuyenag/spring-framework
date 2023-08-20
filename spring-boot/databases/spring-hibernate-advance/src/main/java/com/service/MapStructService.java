package com.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dto.CategoryResDto;
import com.entity.Category;

/*-
 * How to run? 	-> mvn clean install 
 * 				-> View /target/generated-sources/annotations/
 */
@Mapper
public interface MapStructService {

	@Mapping(source = "statusCategory", target = "status")
	@Mapping(source = "createDate", target = "createDate", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "updateTime", target = "updateTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
	CategoryResDto categoryToCategoryDto(Category category);

}
