# Kafka

	kafka port: 9092, zookeeper port: 2181
	
	C:\Server\kafka\zookeeper-3.6.3\conf --------> delete.topic.enable=false
	
	zkserver

	.\bin\windows\kafka-server-start.bat .\config\server.properties

	%KAFKA_HOME%\bin\windows\kafka-server-start.bat %KAFKA_HOME%\config\server.properties

Spring Boot provides a comprehensive enough auto-configuration for Spring Kafka: https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/spring-boot-features.html#boot-features-kafka.

There is this set of beans you can inject into your test class:

	* KafkaTemplate<?, ?> kafkaTemplate
	* ProducerListener<Object, Object> kafkaProducerListener
	* ConsumerFactory<?, ?> kafkaConsumerFactory
	* ProducerFactory<?, ?> kafkaProducerFactory
	* KafkaTransactionManager<?, ?> kafkaTransactionManager
	* KafkaJaasLoginModuleInitializer kafkaJaasInitializer
	* KafkaAdmin kafkaAdmin
	* ConcurrentKafkaListenerContainerFactoryConfigurer kafkaListenerContainerFactoryConfigurer
	* ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory
	* KafkaStreamsConfiguration defaultKafkaStreamsConfig
	* KafkaStreamsFactoryBeanConfigurer kafkaStreamsFactoryBeanConfigurer
