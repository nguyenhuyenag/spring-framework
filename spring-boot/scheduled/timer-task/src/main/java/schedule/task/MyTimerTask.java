package schedule.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("MyTimerTask: " + TimeUtils.now());
	}

	public static void taskRunOnce() {
		MyTimerTask myTimerTask = new MyTimerTask();
		Timer timer = new Timer();
		long delay = 0L;
		timer.schedule(myTimerTask, delay);
	}

	public static void taskRepeate() {
		MyTimerTask myTimerTask = new MyTimerTask();
		Timer timer = new Timer();
		long delay = 2000L;
		timer.schedule(myTimerTask, 0, delay);
	}

	/*-
	 * 	+ Tạo dateSchedule với thời gian là 5:30:00 
	 * 	+ Tạo period với giá trị là số miliseconds trong 24h.
	 */
	public static void repeateConfig() {
		MyTimerTask myTimerTask = new MyTimerTask();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 5);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date firstTime = calendar.getTime(); 	// 5:30
		long period = 24 * 60 * 60 * 1000;		// Cách nhau 24h 
		Timer timer = new Timer();
		timer.schedule(myTimerTask, firstTime, period);
	}

	public static void main(String[] args) {
		// taskRunOnce();
		taskRepeate();
		// repeateConfig();
	}

}
