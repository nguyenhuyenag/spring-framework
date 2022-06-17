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

import com.entity.Data;
import com.repository.DataRepository;
import com.schedule.runnable.DataRunable;
import com.util.PageUtils;
import com.util.RandomUtils;

@Component
public class JobPutData implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(JobPutData.class);

	@Autowired
	private DataRepository repository;

	@Value("${NTHREAD}")
	private int NTHREAD;

	@Value("${JOB_PUT_START:false}")
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
		int n = RandomUtils.randomInteger(20, 30);
		List<Data> listHoaDon = repository.findAllLimit(n);
		if (listHoaDon.isEmpty()) {
			executor.shutdown();
			return;
		}
		List<List<Data>> listToPage = PageUtils.toPages(listHoaDon, NTHREAD);

		taskCompleted = false;

		for (int i = 0; i < NTHREAD; i++) {
			DataRunable sm = new DataRunable(i + 1, listToPage.get(i));
			taskList.add(executor.submit(sm));
		}

		executor.shutdown(); // important
		
		while (!executor.isTerminated()) {
			try {
				LOG.info("Await termination");
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		taskCompleted = true;
		LOG.info("Task completed, waiting job recall");
	}

}