package com.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:kafka-config.properties", encoding = "utf-8")
public class ConfigReader {

//	public static String KAFKA_TOPIC_PRODUCER;
//	public static int JOB_GUI_DANGKY_TIME;
//	public static int JOB_GUI_HOADON_TIME;
//	public static int JOB_SYNC_THONGDIEP_TIME;
//	public static int JOB_SYNC_999_TIME;
//	public static int JOB_SYNC_DATA_FROM_FILE_TIME;
//	public static String MQ_PATH_PREFIX;
//	public static String MST_TVAN;
//	public static int NTHREAD;
//
//	@Value("${MST_TVAN}")
//	private void setMSTTVAN(String MST_TVAN) {
//		ConfigReader.MST_TVAN = MST_TVAN;
//	}
//	
//	@Value("${kafka.topic.producer}")
//	private void setKafkaTopicSend(String topic) {
//		ConfigReader.KAFKA_TOPIC_PRODUCER = topic;
//	}
//	
//	@Value("${job.guidangky.time}")
//	private void setJobTimeGuiDangKy(int time) {
//		ConfigReader.JOB_GUI_DANGKY_TIME = time;
//	}
//
//	@Value("${job.guihoadon.time}")
//	private void setJobTimeHoaDon(int time) {
//		ConfigReader.JOB_GUI_HOADON_TIME = time;
//	}
//	
//	@Value("${job.syncthongdiep.time}")
//	private void setJobTimeSyncThongDiep(int time) {
//		ConfigReader.JOB_SYNC_THONGDIEP_TIME = time;
//	}
//	
//	@Value("${job.sync999.time}")
//	private void setJobTimeSync999(int time) {
//		ConfigReader.JOB_SYNC_999_TIME = time;
//	}
//	
//	@Value("${mq.path.prefix}")
//	private void setMQPathPrefix(String path) {
//		ConfigReader.MQ_PATH_PREFIX = path;
//	}
//	
//	@Value("${nthread}")
//	private void setNThread(int nthread) {
//		ConfigReader.NTHREAD = nthread;
//	}
//	
//	@Value("${job.syncdatafromfile.time}")
//	private void setJobSyncDataFromFile(int time) {
//		ConfigReader.JOB_SYNC_DATA_FROM_FILE_TIME = time;
//	}

}
