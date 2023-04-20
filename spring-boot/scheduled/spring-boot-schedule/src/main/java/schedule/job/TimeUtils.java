package schedule.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	public static String format(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return df.format(date);
	}

}
