package feign.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
