package core.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import core.entity.Clazz;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {

	@Modifying
	@Transactional
	@Query(value = "alter table clazz auto_increment = 1", nativeQuery = true)
	void resetAutoIncrement();

}
