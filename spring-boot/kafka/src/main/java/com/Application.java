package com;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.kafka.core.ConsumerFactory;

import com.service.MessageService;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	/* WAR */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ConsumerFactory<?, ?> consumerFactory;

	public void showTopic() {
		Map<String, Object> config = consumerFactory.getConfigurationProperties();
		try (AdminClient adminClient = AdminClient.create(config);) {
			ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
			listTopicsOptions.listInternal(true);
			System.out.println(adminClient.listTopics(listTopicsOptions).names().get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	void createTopic() {
		Map<String, Object> config = consumerFactory.getConfigurationProperties();
		try (AdminClient adminClient = AdminClient.create(config);) {
			// new NewTopic(topicName, numPartitions, replicationFactor)
			NewTopic newTopic = new NewTopic("topicName2022", 12, (short) 1);
			List<NewTopic> newTopics = Arrays.asList(newTopic);
			adminClient.createTopics(newTopics);
		}
	}

	public void countPartions() {
		Map<String, Object> config = consumerFactory.getConfigurationProperties();
		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);) {
			Map<String, List<PartitionInfo>> topics = consumer.listTopics();
			for (Map.Entry<String, List<PartitionInfo>> entry : topics.entrySet()) {
				// System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				System.out.println("Topic name: " + entry.getKey());
				System.out.println("Partions: " + Integer.toString(entry.getValue().size()) + "\n");
			}
		}
	}

	@Value("${kafka.auto.send:false}")
	boolean isSend;

	@Autowired
	private MessageService messageService;

	@Override
	public void run(String... args) throws Exception {
		if (isSend) {
			messageService.send();
		}
		// countPartions();
	}

}
