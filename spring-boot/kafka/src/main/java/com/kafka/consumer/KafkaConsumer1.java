//package com.kafka.consumer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import com.kafka.config.KafkaConstant;
//import com.service.TVANTDiepMQService;
////import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.TopicPartition;
//
//
//@Component
//public class KafkaConsumer1 {
//
//    
//	@Autowired
//	private TVANTDiepMQService tvanTDiepMQService;
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id1", topicPartitions = { @TopicPartition(topic = "${kafka.topic.consumer}", partitions =  { "1" }) },  //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition1(String message) {
//		System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//	
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id3", topicPartitions = { @TopicPartition(topic = "${kafka.topic.consumer}", partitions =  { "3" }) },  //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition3(String message) {
//		System.out.println("Listener Id3, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//	
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id5", topicPartitions = { @TopicPartition(topic = "${kafka.topic.consumer}", partitions =  { "5" }) },  //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition5(String message) {
//		System.out.println("Listener Id5, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//	
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id7", topicPartitions = { @TopicPartition(topic = "${kafka.topic.consumer}", partitions =  { "7" }) },  //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition7(String message) {
//		System.out.println("Listener Id7, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//	
////	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id9", topicPartitions = { @TopicPartition(topic = "${kafka.topic.consumer}", partitions =  { "9" }) },  //
////			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
////	public void listenPartition9(String message) {
////		System.out.println("Listener Id9, Thread ID: " + Thread.currentThread().getId());
////		tvanTDiepMQService.receivedMessage(message);
////	}
//	
//	
//	
////	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id0", topicPartitions = { @TopicPartition(topic = "${kafka.topic.consumer}", partitions =  { "0", "1", "2" }) }, 
////			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
////    public void listenPartition0(ConsumerRecord<?, ?> record) {
////        System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
////        System.out.println("Received: " + record);
////        countDownLatch0.countDown();
////    }
//
//}
