package oauth2.service;

import java.util.List;

import oauth2.model.User;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);
}
