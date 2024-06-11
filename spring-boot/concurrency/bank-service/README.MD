# Hibernate Locking

# Optimistic Lock

    - Optimistic lock đảm bảo nhiều giao dịch (transaction) có thể hoàn thành mà không ảnh hưởng 
    tới nhau, các transaction tiến hành mà không cần khóa các tài nguyên lại.
    
    - Trước khi commit, mỗi transaction sẽ kiểm tra lại xem dữ liệu của nó có bị transaction khác 
    làm thay đổi không, nếu có thì sẽ quay trở lại trạng thái lúc đầu (rollback).

    - Khi nào sử dụng Optimistic Locking?
        + Khi xung đột dữ liệu ít xảy ra. 
        + Dựa vào một cột phiên bản (version) để kiểm tra xung đột khi cập nhật.

# Pessimistic Lock

    - Khi bắt đầu một transaction, pessimistic lock sẽ khóa dữ liệu mà nó sử dụng lại và chỉ 
    mở khóa khi nó đã sử dụng xong.

    - Khi một entity bị khoá với pessimistic lock, nó sẽ có 2 loại khoá như sau:

        + shared lock – Chỉ có thể đọc nhưng không thể xoá, cập nhật dữ liệu.
        + exclusive lock – Có thể xoá hoặc cập nhật dữ liệu .

    - JPA định nghĩa 3 kiểu pessimistic lock:

        + PESSIMISTIC_READ – cho chúng ta một chìa khoá gọi là shared lock, với khoá này chúng ta 
                            chỉ có thể đọc dữ liệu.
        + PESSIMISTIC_WRITE – nhận một chìa khoá gọi là exclusive lock, chúng ta sẽ có toàn quyền đọc, 
                            xoá, chỉnh sửa dữ liệu và ngăn không cho các transaction khác đọc, xoá, 
                            và cập nhật dữ liệu lên chúng.
        + PESSIMISTIC_FORCE_INCREMENT – giống với cơ chế hoạt động của PESSIMISTIC_WRITE, ngoài ra 
                            nó còn tăng giá trị của thuộc tính version trong entity.

    - Khi nào sử dụng Pessimistic Locking?
        + Khi xung đột dữ liệu có khả năng xảy ra cao.
        + Dùng các khóa để ngăn các giao dịch khác truy cập vào dữ liệu bị khóa cho đến 
          khi khóa được giải phóng.

# https://viblo.asia/p/tai-sao-phai-dung-bigdecimal-khi-tinh-toan-ve-tien-te-trong-java-oOVlYqRnl8W
