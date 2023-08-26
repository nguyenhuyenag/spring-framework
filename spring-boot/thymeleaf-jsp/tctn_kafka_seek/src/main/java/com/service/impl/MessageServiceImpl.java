package com.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import com.dto.SeekFilter;
import com.request.SeekRequest;
import com.response.SeekResponse;
import com.response.UnConsumer;
import com.service.MessageService;
import com.tgdd.service.TGDDTVANGuiHoaDonService;
import com.ts24.repository.TS24TVANTDiepMQRepository;
import com.ts24.service.TS24TVANGuiHoaDonService;
import com.util.Base64Utils;
import com.util.ConfigReader;
import com.util.DatetimeUtils;
import com.util.JsonUtils;
import com.util.XmlUtils;

@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	private ConsumerFactory<?, ?> consumerFactory;

	// TS24
	@Autowired
	private TS24TVANGuiHoaDonService ts24TVANGuiHoaDonService;

	// TGDD
	@Autowired
	private TGDDTVANGuiHoaDonService tgddTVANGuiHoaDonService;

	@Value("${OFFSET_TIME}")
	private int OFFSET_TIME;

	@Value("${RECEIVE_URL}")
	private String RECEIVE_URL;

	@Value("${kafka.topic.consumer}")
	private String topic;

	@Value("${DS_MA_LOAI}")
	private List<String> DS_MA_LOAI;

	private static long total = 0;

	@Autowired
	private TS24TVANTDiepMQRepository ts24TVANTDiepMQRepository;

	private boolean hasResult(List<String> listMaLoai) {
		if (listMaLoai.size() == 0) {
			return false;
		}
		for (String maloai : listMaLoai) {
			if (DS_MA_LOAI.contains(maloai)) {
				return true;
			}
		}
		return false;
	}

	private void doPost(String message) {
		try (CloseableHttpClient client = HttpClients.createDefault();) {
			Map<String, String> map = new HashMap<>();
			map.put("content", message);
			StringEntity entity = new StringEntity(JsonUtils.toJSON(map));
			HttpPost httpPost = new HttpPost(RECEIVE_URL);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(httpPost);
			LOG.info("Status: {}", response.getStatusLine().toString());
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send(String message) {
		doPost(message);
	}

	@Override
	public boolean seek(SeekRequest hoadon) {
		long startTimestamp = hoadon.getLongtime() - OFFSET_TIME * 60000;
		long endTimestamp = hoadon.getLongtime() + 60 * 60000;
		Consumer<?, ?> consumer = consumerFactory.createConsumer();
		Set<TopicPartition> partitions = new HashSet<>();
		for (PartitionInfo p : consumer.partitionsFor(topic)) {
			partitions.add(new TopicPartition(p.topic(), p.partition()));
		}
		consumer.assign(partitions);
		Map<TopicPartition, Long> timestamps = new HashMap<>();
		for (TopicPartition tp : partitions) {
			timestamps.put(tp, startTimestamp);
		}
		Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(timestamps);
		for (TopicPartition tp : partitions) {
			if (offsets.get(tp) != null) {
				consumer.seek(tp, offsets.get(tp).offset());
			}
		}
		LOG.info("Seek: {}", hoadon.getMathongdiep());
		LOG.info("Seek from: {}, to: {}", startTimestamp, endTimestamp);
		while (true) {
			final ConsumerRecords<?, ?> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<?, ?> record : records) {
				String xml = record.value().toString();
				String mtdieptchieu = XmlUtils.getTextOfTag(xml, "TTChung", "MTDTChieu");
				String mltdiep = XmlUtils.getTextOfTag(xml, "TTChung", "MLTDiep");
				// "Partition: " + record.partition() + ",\tValue: " + record.value() +
				// ",\tTime: " + record.timestamp())
				if (StringUtils.isNotEmpty(mtdieptchieu)) {
					LOG.info("Seek mtdieptchieu: {}, time = {}", mtdieptchieu, record.timestamp());
					if (hoadon.getMathongdiep().trim().equals(mtdieptchieu.trim())) {
						LOG.info("Seek success: {}, mltdiep = {}", hoadon.getMathongdiep(), mltdiep);
						if (!"999".equals(mltdiep) && !"-1".equals(mltdiep)) {
							String base64 = Base64Utils.encodeToString(xml);
							send(base64);
							return true;
						}
					}
					if (record.timestamp() > endTimestamp) {
						LOG.info("Seek not found: {}", hoadon.getMathongdiep());
						consumer.close();
						return false;
					}
				}
			}
		}
	}

	@Override
	public List<SeekResponse> seekMultiple(List<SeekRequest> seekList) {
		List<SeekResponse> res = new ArrayList<>();
		for (SeekRequest hoadon : seekList) {
			long startTime = hoadon.getLongtime() - OFFSET_TIME * 60000;
			long endTime = hoadon.getLongtime() + 60 * 60000;
			Consumer<?, ?> consumer = setConsumer(startTime);
			boolean hasResult = doSeek(consumer, hoadon.getMathongdiep(), startTime, endTime);
			res.add(new SeekResponse(hoadon.getMathongdiep(), hasResult));
		}
		return res;
	}

	private Consumer<?, ?> setConsumer(long startTime) {
		Consumer<?, ?> consumer = consumerFactory.createConsumer();
		Set<TopicPartition> partitions = new HashSet<>();
		for (PartitionInfo p : consumer.partitionsFor(topic)) {
			partitions.add(new TopicPartition(p.topic(), p.partition()));
		}
		consumer.assign(partitions);
		Map<TopicPartition, Long> timestamps = new HashMap<>();
		for (TopicPartition tp : partitions) {
			timestamps.put(tp, startTime);
		}
		Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(timestamps);
		for (TopicPartition tp : partitions) {
			if (offsets.get(tp) != null) {
				consumer.seek(tp, offsets.get(tp).offset());
			}
		}
		return consumer;
	}

	private boolean doSeek(Consumer<?, ?> consumer, String mathongdiep, long startTime, long endTime) {
		LOG.info("Seek {}, from {} to {}", mathongdiep, startTime, endTime);
		while (true) {
			final ConsumerRecords<?, ?> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<?, ?> record : records) {
				String xml = record.value().toString();
				String mtdieptchieu = XmlUtils.getTextOfTag(xml, "TTChung", "MTDTChieu");
				String mltdiep = XmlUtils.getTextOfTag(xml, "TTChung", "MLTDiep");
				if (StringUtils.isNotEmpty(mtdieptchieu)) {
					LOG.info("Seek mtdieptchieu: {}, time = {}", mtdieptchieu, record.timestamp());
					if (mathongdiep.trim().equals(mtdieptchieu.trim())) {
						LOG.info("Seek success: {}, mltdiep = {}", mathongdiep, mltdiep);
						if (!"999".equals(mltdiep) && !"-1".equals(mltdiep)) {
							String base64 = Base64Utils.encodeToString(xml);
							send(base64);
							return true;
						}
					}
					if (record.timestamp() > endTime) {
						LOG.info("Seek not found: {}", mathongdiep);
						consumer.close();
						return false;
					}
				}
			}
		}
	}

	@Override
	public Set<SeekRequest> findAllTuNgayDenNgay(String database, String matdiep, String fromdate, String todate) {
		Set<SeekRequest> result = new HashSet<>();
		if (!StringUtils.isAllEmpty(fromdate, todate)) {
			try {
				fromdate = URLDecoder.decode(fromdate, StandardCharsets.UTF_8.name());
				todate = URLDecoder.decode(todate, StandardCharsets.UTF_8.name());
				fromdate = fromdate.replaceAll("T", " ");
				todate = todate.replaceAll("T", " ");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.isAnyEmpty(fromdate, todate)) {
			fromdate = DatetimeUtils.format(DatetimeUtils.yesterday());
			todate = DatetimeUtils.format(new Date());
		}

		LOG.info("Search: database = {}, from: {}, to: {}", database, fromdate, todate);

		List<SeekFilter> seekList = new ArrayList<>();
		if ("tgdd".equalsIgnoreCase(database)) {
			if (StringUtils.isNotEmpty(matdiep)) {
				seekList = tgddTVANGuiHoaDonService.findByMaTDiep(matdiep);
			} else {
				seekList = tgddTVANGuiHoaDonService.findByTuNgayDenNgay(fromdate, todate);
			}
		} else { // TS24
			if (StringUtils.isNotEmpty(matdiep)) {
				seekList = ts24TVANGuiHoaDonService.findByMaTDiep(matdiep);
			} else {
				seekList = ts24TVANGuiHoaDonService.findByTuNgayDenNgay(fromdate, todate);
			}
		}

		for (SeekFilter hoadon : seekList) {
			if (!hoadon.getMaloaitdiep().contains("999")) {
				List<String> listMaLoai = ts24TVANTDiepMQRepository.findMaLoaiByMaTDiepTChieu(hoadon.getMatdiep());
				if (!hasResult(listMaLoai)) {
					String ngayguitct = DatetimeUtils.format(hoadon.getNgayGuiTct()); // yyyy-MM-dd HH:mm:ss
					SeekRequest tracuu = new SeekRequest(hoadon.getMatdiep(), ngayguitct,
							hoadon.getNgayGuiTct().getTime());
					result.add(tracuu);
				}
			}
		}
		return result;
	}

	private Set<TopicPartition> listTopicPartition() {
		Consumer<?, ?> consumer = consumerFactory.createConsumer();
		Set<TopicPartition> partitions = new HashSet<>();
		List<PartitionInfo> listPInfo = consumer.partitionsFor(ConfigReader.KAFKA_TOPIC_CONSUMER);
		for (PartitionInfo pif : listPInfo) {
			partitions.add(new TopicPartition(pif.topic(), pif.partition()));
		}
		return partitions;
	}

	@Override
	public List<UnConsumer> countUnreadMessage() {
		List<UnConsumer> list = new ArrayList<>();
		Set<TopicPartition> partitions = listTopicPartition();
		Map<String, Object> config = consumerFactory.getConfigurationProperties();
		try (AdminClient adminClient = AdminClient.create(config);
				KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);) {
			Map<TopicPartition, Long> endOffsets = consumer.endOffsets(partitions);
			Map<TopicPartition, OffsetAndMetadata> committed = consumer.committed(partitions);
//			KafkaFuture<Map<TopicPartition, OffsetAndMetadata>> kafkaFuture = adminClient
//					.listConsumerGroupOffsets(KafkaConstant.CONSUMER_GROUP_ID) //
//					.partitionsToOffsetAndMetadata();
//			Map<TopicPartition, OffsetAndMetadata> committed = kafkaFuture.get();
			committed.forEach((k, v) -> {
				if (v != null) {
					long committedOffset = v.offset();
					long endOffset = endOffsets.get(k);
					long lag = endOffset - committedOffset;
					LOG.info("Partition: {}, committedOffset={}, endOffset={}, lag={}", k.partition(), committedOffset, endOffset, lag);
					total += lag;
					list.add(new UnConsumer(k.partition(), lag));
				} else {
					LOG.info("Partition={}, offsetAndMetadata if null", k.partition());
				}
			});
		}
		LOG.info("Total lag: {}", total);
		total = 0;
		Collections.sort(list, (a, b) -> a.getPartition() - b.getPartition());
		return list;
	}

}
