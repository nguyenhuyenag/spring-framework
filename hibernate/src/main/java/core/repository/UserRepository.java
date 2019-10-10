package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.idclass.User;
import core.entity.idclass.UserId;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

}
