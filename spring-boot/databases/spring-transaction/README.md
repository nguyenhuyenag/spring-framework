# Spring Transaction
	
# Propagation
   
    - REQUIRED: Nếu có một transaction đang hoạt động thì nó sẽ sử dụng chung,
        nếu không method được gọi sẽ tạo một transaction mới. Đây là giá trị mặc định
        của Propagation.
   
    - SUPPORTS: Sử dụng lại transaction hiện đang hoạt động. Nếu không thì method
        được gọi sẽ thực thi mà không được đặt trong một transactional context nào
   
    - MANDATORY: Yêu cầu phải có một transaction đang hoạt động trước khi gọi,
        nếu không method được gọi sẽ ném ra một exception.
   
    - NEVER: Ném một exception nếu method được gọi trong một transaction hoạt động.
   
    - NOT_SUPPORTED: Dừng transaction hiện tại và thực thi method mà không thuộc
        một transaction nào
   
    - REQUIRES_NEW: Luôn bắt đầu một transaction mới cho method được gọi. Nếu
        method được gọi với một transaction đang hoạt động, transaction đó sẽ bị tạm
        ngưng, một transaction mới sẽ được tạo và sử dụng cho method này. Transaction
        mới vừa được tạo sẽ thực thi độc lập với transaction bên ngoài, khi
        transaction này kết thúc dữ liệu sẽ được đồng bộ xuống database. Sau đó
        transaction bên ngoài sẽ được kích hoạt và hoạt động trở lại.
    - NESTED: Method được gọi sẽ tạo một transaction mới nếu không có transaction
        nào đang hoạt động. Nếu method được gọi với một transaction đang hoạt động
        Spring sẽ tạo một savepoint và rollback tại đây nếu có Exception xảy ra.

# Mức độ cô lập giao dịch (Transaction Isolation Levels)

    - Định nghĩa: @Transactional(isolation = Isolation.READ_COMMITTED)

    Các mức độ cô lập giao dịch phổ biến bao gồm:

     + READ UNCOMMITTED: Cho phép đọc dữ liệu chưa được commit từ các giao dịch khác, dễ gây 
        ra "dirty reads".
    
     + READ COMMITTED: Chỉ cho phép đọc dữ liệu đã được commit, ngăn chặn "dirty reads".
    
     + REPEATABLE READ: Đảm bảo rằng dữ liệu đọc trong một giao dịch không thay đổi trong suốt 
        quá trình giao dịch, ngăn chặn "non-repeatable reads".
    
     + SERIALIZABLE: Đảm bảo các giao dịch được thực hiện tuần tự, ngăn chặn tất cả các vấn đề 
        về đồng thời.

- @Transactional(readOnly = true): Đánh dấu phương thức getUserById để chỉ thực hiện các thao tác đọc, 
  không cần lock cơ sở dữ liệu