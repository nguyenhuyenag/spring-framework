# Spring Transaction

# Mức độ cô lập giao dịch (Transaction Isolation Levels)

        @Transactional(isolation = Isolation.READ_COMMITTED)
        public User createUser(User user) {
            return userRepository.save(user);
        }

    Các mức độ cô lập giao dịch phổ biến bao gồm:

     + READ UNCOMMITTED: Cho phép đọc dữ liệu chưa được commit từ các giao dịch khác, dễ gây 
        ra "dirty reads".
    
     + READ COMMITTED: Chỉ cho phép đọc dữ liệu đã được commit, ngăn chặn "dirty reads".
    
     + REPEATABLE READ: Đảm bảo rằng dữ liệu đọc trong một giao dịch không thay đổi trong suốt 
        quá trình giao dịch, ngăn chặn "non-repeatable reads".
    
     + SERIALIZABLE: Đảm bảo các giao dịch được thực hiện tuần tự, ngăn chặn tất cả các vấn đề 
        về đồng thời.
	
# Các chiến lược quản lý giao dịch (Propagation)

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public User createUser(User user) {
            return userRepository.save(user);
        }
   
    - REQUIRED: Mặc định, sử dụng transaction hiện tại hoặc tạo mới nếu không có transaction nào.
   
    - REQUIRES_NEW: Luôn tạo một transaction mới, bất kể có giao dịch hiện tại hay không.

    - NESTED: Tạo một giao dịch lồng trong giao dịch hiện tại.

    - MANDATORY: Bắt buộc phải có transaction hiện tại, nếu không sẽ ném ra exception.
    
    - SUPPORTS: Thực thi method trong transaction hiện tại nếu có, nếu không thì không cần transaction.
   
    - NOT_SUPPORTED: Dừng transaction hiện tại và thực thi method mà không thuộc một transaction nào.
   
    - NEVER: Thực thi phương thức mà không cần giao dịch, ném ngoại lệ nếu có giao dịch hiện tại.

- @Transactional(readOnly = true): Đánh dấu phương thức getUserById để chỉ thực hiện các thao tác đọc, 
  không cần lock cơ sở dữ liệu