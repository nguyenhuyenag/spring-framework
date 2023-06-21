package oauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import oauth2.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
