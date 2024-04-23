package com.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dto.CategoryResDto;
import com.entity.Category;

/*-
 * How to run? 	-> mvn clean install 
 * 				-> View /target/generated-sources/annotations/
 */
@Mapper(componentModel = "spring") // Đánh dấu một mapping interface và cho phép MapStrust hoạt động trên interface này
public interface MapStructService {

	// @Mapping(target = "status", ignore = true)
	@Mapping(source = "statusCategory", target = "status") // Map 2 field khác nhau
	@Mapping(source = "createDate", target = "createDate", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "updateTime", target = "updateTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
	CategoryResDto categoryToCategoryDto(Category category);

}
