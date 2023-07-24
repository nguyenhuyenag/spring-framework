package com.bakup;
//package com.schedule.jobs;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.service.DataService;
//
//@Component
//public class JobInsertData implements Job {
//
//	private static final Logger LOG = LoggerFactory.getLogger(JobInsertData.class);
//
//	@Autowired
//	private DataService service;
//
//	@Value("${JOB_INSERT_START:false}")
//	private boolean startJob;
//
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//		if (startJob) {
//			LOG.info("Start JobInsert");
//			service.autoInsert();
//		}
//	}
//
//}