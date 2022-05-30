package com.schedule.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.model.HoaDon;
import com.schedule.runnable.HoaDonRunable;
import com.service.HoaDonService;
import com.util.PageUtils;

@Component
public class PutHoaDon implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(PutHoaDon.class);

	@Autowired
	private HoaDonService service;

	@Value("${NTHREAD}")
	private int NTHREAD;

	@Value("${LIMIT_QUERY}")
	private int LIMIT_QUERY;

	@Value("${IS_SEND}")
	private boolean isSend;

	public static int nJob = 0; // count job
	private static boolean taskCompleted = true;
	// private static int count = 0; // count thread completed

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if (isSend) {
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

		List<HoaDon> listHoaDon = service.findAllWithLimit(LIMIT_QUERY);
		if (listHoaDon.isEmpty()) {
			LOG.info("Reset database ...");
			service.reset();
			return;
		}
		List<List<HoaDon>> listToPage = PageUtils.toPages(listHoaDon, NTHREAD);

		taskCompleted = false;

		for (int i = 0; i < NTHREAD; i++) {
			HoaDonRunable sm = new HoaDonRunable(i + 1, listToPage.get(i));
			taskList.add(executor.submit(sm));
		}

		executor.shutdown();
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