# Spring Boot Schedule

- Cron expression is represented by six fields:

	second, minute, hour, day-of-month, month, day-of-week
	
		*		Tất cả cả trường. Ví dụ nếu sử dụng ở trường hour có nghĩa là chạy hàng giờ
		? 		Nhận giá trị bất kỳ
		- 		Khoảng được chạy. Ví dụ, "10-12" trong hour có nghĩa là chạy từ 10h đến 12h
		, 		Liệt kê các giá trị. Ví dụ, “MON,WED,FRI” trong trường day-of-week có nghĩa là thứ hai, tư, sáu
		/ 		Chỉ rõ số lần tăng. Ví dụ, "0/15" hoặc "*/15" ở second nghĩa là cứ 15s chạy một lần
		# 		Chỉ thứ tự của ngày trong tháng. Ví dụ “6#3” ở day-of-week là ngày thứ sáu lần thứ 3 trong tháng.
		L(last) - Ở day-of-month thì có nghĩa là ngày cuối cùng của tháng
				
				- Ở day-of-week thì có nghĩa là ngày thứ bảy, nếu có 1 giá trị trước L, ví dụ “6L” tức là ngày thứ 6 cuối cùng của tháng
		
		Các ký tự trong trường month và day-of-week không phân biệt chữ hoa/thường, fri giống với FRI

	"0 0 * * * *" 			= the top of every hour of every day.
	"*/10 * * * * *" 		= every 1 seconds.
	"0 0 8-10 * * *" 		= 8, 9 and 10 o'clock of every day.
	"0 0 8,10 * * *" 		= 8 and 10 o'clock of every day.
	"0 0/30 8-10 * * *" 	= 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
	"0 0 9-17 * * MON-FRI" 	= on the hour nine-to-five weekdays
	"0 0 0 25 12 ?" 		= every Christmas Day at midnight
	"5-10/1 * 12-14 * * MON-FRI"	From the 5th - 10th second, In the time frame of 12pm-2pm from Monday to Friday, there is a repetition every 1 second