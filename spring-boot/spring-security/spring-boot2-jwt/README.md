# Spring Boot JWT

- Định dạng: Header.Payload.Signature 
	
		-> <base64-encoded header>.<base64-encoded payload>.<base64-encoded signature>

	JWTs có thể được ký bằng:
		
		- Khóa bí mật (với thuật toán HMAC) 
		- Public / private key sử dụng mã hoá RSA.

	+ Header: Có 2 phần, đó là: loại mã token, đó là JWT; và thuật toán được sử dụng, chẳng hạn HMAC SHA256 hoặc RSA.

		Ví dụ:
		{
		  "alg": "HS256",
		  "typ": "JWT"
		}

	-> Sau đó, JSON này được mã hóa Base64Url để tạo thành phần đầu tiên của JWT.
	
	+ Payload: Chứa các claims (thông tin) của. Có 3 loại claims thường gặp:
	
		Loại				Ý nghĩa
		Registered Claims	Các claim được chuẩn hóa sẵn trong JWT: iss, sub, aud, exp,...
		Public Claims		Các claim được công khai/đăng ký để sử dụng chung, nhằm tránh xung đột tên.
		Private Claims		Các claim do hai bên tự thỏa thuận, chỉ hệ thống của họ hiểu.
	
		{
			"iss": "https://auth.example.com",  // issuer: bên phát hành token
			"sub": "user123",                   // subject: chủ thể của token
			"aud": "payment-api",               // audience: đối tượng mà token được cấp cho
			"iat": 1757900000,                  // issued-at time: thời điểm phát hành token
			"exp": 1757903600                   // expiration time: thời điểm token hết hạn
		}
	
		** Nếu đem token đó gửi sang một API khác:

			/payment-api

			thì payment-api có thể kiểm tra:

			aud == "payment-api" ?
	
	+ Signature: Mã hóa 2 chuỗi ở trên theo công thức:

		HMACSHA256(	// Thuật toán được chỉ định trong header
		  base64UrlEncode(header) + "." +
		  base64UrlEncode(payload),
		  secret)

	=> Cuối cùng là ghép 3 chuỗi lại: xxxxx.yyyyy.zzzzz

- Spring Security

	To call the method by any of the role mentioned use
		
		@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_TIMER_TASK')")
	
	and enable pre- and post- annotations in security class
		
		@EnableGlobalMethodSecurity(prePostEnabled = true)

- Authentication (Xác thực người dùng) và Authorization (Ủy quyền truy cập vào tài nguyên)
		
- Không nên lưu list role vào token, nên truy vấn database để lấy role mỗi khi authentication thành công?
