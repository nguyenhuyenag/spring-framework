package schedule.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "scheduling.enabled", havingValue = "true", matchIfMissing = true)
public class FixedDelay {

	@Value("${time.repeate:0}")
	private int repeatTime;

	/*-
	 * - fixedDelay: Chỉ khi task trước đó hoàn thành thì mới chạy tiếp task đó lại lần nữa.
	 * 
	 * - Các thuộc tính trong @Scheduled
	 * 
	 * 		+ initialDelay = 5000 						Task sẽ chạy sau khi ứng dụng được khởi động được 5 giây
	 * 		+ fixedDelay 	= 5000 						Thời gian task sẽ chạy lại 
	 * 		+ fixedDelayString = "${time.repeate}"		Thời gian task sẽ chạy lại kiểu string
	 */
	@Scheduled(cron = "10 * * ? * *") // chạy vào giây thứ 10 của mỗi phút
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("Fixed delay task - " + TimeUtils.format(new Date()));
	}

}
