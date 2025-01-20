package feign.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {

    private int id;
    private String email;
    private String password;
    private String name;
    private String role;
    private String avatar;
    private LocalDateTime creationAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", avatar='" + avatar + '\'' +
                ", creationAt=" + creationAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
