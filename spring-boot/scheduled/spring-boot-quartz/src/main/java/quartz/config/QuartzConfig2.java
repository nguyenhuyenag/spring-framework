//package quartz.config;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.quartz.Job;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SimpleScheduleBuilder;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.StdSchedulerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
// // -> Cách 2
//@Configuration
//public class QuartzConfig2 {
//
//	private static final Logger LOG = LoggerFactory.getLogger(QuartzConfig2.class);
//
//	@Bean
//	public SpringBeanJobFactory springBeanJobFactory() {
//		return new SpringBeanJobFactory();
//	}
//
//	@Bean
//	public Scheduler scheduler(Map<String, JobDetail> jobMap, Set<? extends Trigger> triggers)
//			throws SchedulerException, IOException {
//		StdSchedulerFactory factory = new StdSchedulerFactory();
//		LOG.debug("Getting a handle to the Scheduler");
//		Scheduler scheduler = factory.getScheduler();
//		scheduler.setJobFactory(springBeanJobFactory());
//		Map<JobDetail, Set<? extends Trigger>> triggersAndJobs = new HashMap<>();
//		for (JobDetail jobDetail : jobMap.values()) {
//			for (Trigger trigger : triggers) {
//				if (trigger.getJobKey().equals(jobDetail.getKey())) {
//					LOG.debug("trigger.getJobKey(): " + trigger.getJobKey());
//					LOG.debug("jobDetail.getKey(): " + jobDetail.getKey());
//					triggersAndJobs.put(jobDetail, new HashSet<>(Arrays.asList(trigger)));
//				}
//			}
//		}
//		scheduler.scheduleJobs(triggersAndJobs, true);
//		LOG.debug("Starting Scheduler threads");
//		scheduler.start();
//		return scheduler;
//	}
//
//	public static JobDetail createJobDetail(Class<? extends Job> classJob) {
//		LOG.debug("createJobDetail(jobClass={})", classJob.getSimpleName());
//		return JobBuilder.newJob(classJob) //
//				.storeDurably(true) // here !!!!!
//				.build();
//	}
//
//	public static Trigger createTrigger(JobDetail jobDetail, int repeatTime) {
//		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder //
//				.simpleSchedule() //
//				.withIntervalInSeconds(repeatTime).repeatForever();
//		return TriggerBuilder.newTrigger() //
//				.forJob(jobDetail) //
//				.withSchedule(scheduleBuilder) //
//				.build();
//	}
//
//}
