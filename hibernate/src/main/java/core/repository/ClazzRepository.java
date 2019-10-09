package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.Clazz;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {

}
