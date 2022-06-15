package com.schedule.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.model.Ipsum;
import com.schedule.runnable.HoaDonRunable;
import com.service.DataService;
import com.util.PageUtils;

@Component
public class PutJobs implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(PutJobs.class);

	@Autowired
	private DataService service;

	@Value("${NTHREAD}")
	private int NTHREAD;

	@Value("${START_JOB:false}")
	private boolean startJob;

	public static int nJob = 0; // count job
	private static boolean taskCompleted = true;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if (startJob) {
			if (taskCompleted) {
				nJob++;
				LOG.info("Job {} start", nJob);
				send();
			} else {
				LOG.info("Job recall but taskCompleted = {}", taskCompleted);
			}
		}
	}

	private void send() {
		List<Future<?>> taskList = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(NTHREAD);

		List<Ipsum> listHoaDon = null; // service.findAllWithLimit(randomIntFrom(100, 200));
		if (listHoaDon.isEmpty()) {
			// service.reset();
			return;
		}
		List<List<Ipsum>> listToPage = PageUtils.toPages(listHoaDon, NTHREAD);

		taskCompleted = false;

		for (int i = 0; i < NTHREAD; i++) {
			HoaDonRunable sm = new HoaDonRunable(i + 1, listToPage.get(i));
			taskList.add(executor.submit(sm));
		}

		// executor.shutdown();
		while (!executor.isTerminated()) {
			try {
				LOG.info("Await termination");
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// executor.shutdown();
		LOG.info("Job {} end", nJob);
		try {
			LOG.info("Sleep 10 second...");
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		taskCompleted = true;
	}

}