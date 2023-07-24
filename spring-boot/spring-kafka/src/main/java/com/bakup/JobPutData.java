package com.bakup;

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

		List<Data> listHoaDon = repository.findAllLimit(200);
		if (listHoaDon.isEmpty()) {
			LOG.info("No record!");
			executor.shutdown();
			return;
		}

		taskCompleted = false;

		List<List<Data>> listToPage = null; // PageUtils.toPages(listHoaDon, NTHREAD);
		for (int i = 0; i < NTHREAD; i++) {
			@SuppressWarnings("null")
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