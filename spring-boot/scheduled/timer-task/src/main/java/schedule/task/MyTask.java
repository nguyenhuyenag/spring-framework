package schedule.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("TaskRunOnce: " + TimeUtils.now());
	}

	public static void runOnce() {
		MyTask myTask = new MyTask();
		Timer timer = new Timer();
		long delay = 0L;
		timer.schedule(myTask, delay);
	}

	public static void repeate() {
		MyTask myTask = new MyTask();
		Timer timer = new Timer();
		long delay = 2000L;
		timer.schedule(myTask, 0, delay);
	}

	/*-
	 * 	+ Tạo dateSchedule với thời gian là 5:30:00 
	 * 	+ Tạo period với giá trị là số miliseconds trong 24h.
	 */
	public static void repeateConfig() {
		MyTask myTask = new MyTask();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 5);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date firstTime = calendar.getTime();
		long period = 24 * 60 * 60 * 1000;
		Timer timer = new Timer();
		timer.schedule(myTask, firstTime, period);
	}

	public static void main(String[] args) {
		// runOnce();
		// repeate();
		repeateConfig();
	}

}
