package jwt.dto;

import java.util.List;

import jwt.entity.Role;
import lombok.Data;

@Data
public class UserResponseDTO {

	private Integer id;
	private String username;
	private String email;
	List<Role> roles;

}
