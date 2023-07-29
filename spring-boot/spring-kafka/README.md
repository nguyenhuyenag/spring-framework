# Kafka

- https://kafka.apache.org/downloads.html (download the binary file)


	zookeeper port: 2181, kafka port: 9092
	
	C:\Server\kafka\zookeeper-3.6.3\conf --------> delete.topic.enable=false

- There is this set of beans you can inject into your test class:

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
