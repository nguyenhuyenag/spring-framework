package com.core.other;

/*-
    - wait(): Phương thức này khi được gọi, nó sẽ làm Thread đang nắm giữ Lock trên
    đối tượng phải trả Lock này lại cho Monitor của đối tượng đó. Đồng thời
    Thread đó rơi vào trạng thái ngủ, cho một Thread nào đó khác “đánh thức” bằng
    một trong hai phương thức dưới đây, hoặc tự dậy khi hết thời gian ngủ nếu gọi
    đến wait(long).

    - notify(): Phương thức này giúp “đánh thức” Thread đã vào trạng ngủ bởi phương
    thức wait(). Nếu có nhiều Thread cùng gọi đến wait(), tức là cùng bị ngủ khi
    gọi đến đối tượng này, phương thức notify() sẽ đánh thức bất kỳ Thread nào
    trong các Thread đang ngủ.

    - notifyAll(): Phương thức này mở rộng hơn cho notify(). Nó giúp “đánh thức”
    tất cả các Thread nào đã gọi đến wait() bên trong đối tượng này.
 */
public class CoopMain {

}
