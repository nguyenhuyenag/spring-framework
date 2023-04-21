package schedule.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "scheduling.enabled", havingValue = "true", matchIfMissing = true)
public class JobFixedDelay {

	@Value("${time.repeate:0}")
	private int repeatTime;

	/*-
	 * - fixedDelay: Chỉ khi nào task trước đó thực hiện xong thì nó mới chạy tiếp
	 * task đó lại lần nữa. Ví dụ sau 1 giây mà method scheduleFixedDelayTask chưa
	 * chạy xong thì nó sẽ chờ cho tới khi nào xong mới chạy lại lần tiếp theo
	 * 
	 * - Các thuộc tính trong @Scheduled
	 * 
	 * initialDelay = 5000 						Task sẽ chạy sau khi ứng dụng được khởi động được 5 giây
	 * fixedDelay = 5000 						Thời gian task sẽ chạy lại 
	 * fixedDelayString = "${time.repeate}"		Thời gian task sẽ chạy lại kiểu string
	 */
	// @Scheduled(cron = "10 * * * * ?") // chạy vào giây thứ 10 của mỗi phút
	@Scheduled(cron = "10 * * ? * *") // chạy vào giây thứ 10 của mỗi phút
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("Fixed delay task - " + TimeUtils.format(new Date()));
	}

}
