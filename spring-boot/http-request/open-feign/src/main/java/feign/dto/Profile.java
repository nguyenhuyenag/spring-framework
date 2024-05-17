package feign.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Profile {

    private int id;
    private String email;
    private String password;
    private String name;
    private String role;
    private String avatar;

}
