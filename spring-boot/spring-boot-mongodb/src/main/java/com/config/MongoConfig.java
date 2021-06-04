package com.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

//@Configuration
//public class MongoConfig  implements InitializingBean {
//	
//	@Autowired
//    @Lazy
//    private MappingMongoConverter mappingMongoConverter;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
//    }
//
//}

@Configuration
public class MongoConfig {

	@Autowired
	private MappingMongoConverter converter;

	// remove `_class`
	@PostConstruct
	public void setUpMongoEscapeCharacterConversion() {
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
	}

}
