package com.schedule.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

	public static int jobCount = 0; // count job
	// private static int count = 0; // count thread completed

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		jobCount++;
		LOG.info("Job {} start", jobCount);
		send();
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

		for (int i = 0; i < NTHREAD; i++) {
			HoaDonRunable sm = new HoaDonRunable(i + 1, listToPage.get(i));
			taskList.add(executor.submit(sm));
		}
		
		executor.shutdown();
		while (!executor.isTerminated()) {
			try {
				LOG.info("In While, thread = {}, task completed = {}", NTHREAD, countTaskCompleted(taskList));
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		LOG.info("Job {} end, countSend = {}, total = {}", jobCount, HoaDonRunable.getCountSend(), listHoaDon.size());

//		while (true) {
//			if (isCompleted(taskList)) {
//				LOG.info("Job {} end, countSend = {}, total = {}", jobCount, HoaDonRunable.getCountSend(), listHoaDon.size());
//				break;
//			} else {
//				try {
//					LOG.info("In While, thread = {}, task completed = {}", NTHREAD, countTaskCompleted(taskList));
//					TimeUnit.SECONDS.sleep(2);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}

	}

	private boolean isCompleted(List<Future<?>> taskList) {
		return countTaskCompleted(taskList) == taskList.size();
	}

	private int countTaskCompleted(List<Future<?>> taskList) {
		int c = 0;
		for (Future<?> f : taskList) {
			try {
				if (f.get() == null) {
					c++;
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return c;
	}

}