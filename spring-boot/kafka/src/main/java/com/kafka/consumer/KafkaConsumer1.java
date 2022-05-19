//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//
//import com.kafka.KafkaConstant;
//import com.service.TVANTDiepMQService;
//
//@Component
//public class KafkaConsumer1 {
//	
//	@Autowired
//	private TVANTDiepMQService tvanTDiepMQService;
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id0", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "0" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition0(String message) {
//		System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id1", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "1" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition1(String message) {
//		System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id2", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "2" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition2(String message) {
//		System.out.println("Listener Id2, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id3", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "3" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition3(String message) {
//		System.out.println("Listener Id3, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id4", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "4" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition4(String message) {
//		System.out.println("Listener Id4, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id5", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "5" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition5(String message) {
//		System.out.println("Listener Id5, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id6", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "6" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition6(String message) {
//		System.out.println("Listener Id6, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id7", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "7" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition7(String message) {
//		System.out.println("Listener Id7, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id8", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "8" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition8(String message) {
//		System.out.println("Listener Id8, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id9", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "9" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition9(String message) {
//		System.out.println("Listener Id9, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id10", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "10" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition10(String message) {
//		System.out.println("Listener Id10, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//
//	@KafkaListener(autoStartup = "${kafka.auto.startup}", id = "id11", topicPartitions = {
//			@TopicPartition(topic = "${kafka.topic.consumer}", partitions = { "11" }) }, //
//			groupId = KafkaConstant.GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//	public void listenPartition11(String message) {
//		System.out.println("Listener Id11, Thread ID: " + Thread.currentThread().getId());
//		tvanTDiepMQService.receivedMessage(message);
//	}
//}