package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.MySqlName;

@Repository
public interface NameRepository extends JpaRepository<MySqlName, Integer> {

}
