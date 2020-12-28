package quartz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quartz.entity.Quartz;

@Repository
public interface QuartzRepository extends JpaRepository<Quartz, Integer> {

//	@Query("select max(t.id) from quartz t")
//	Optional<Integer> findMaxById();
//
//	@Modifying
//	@Transactional
//	@Query(value = "alter table quartz auto_increment = 1", nativeQuery = true)
//	void resetId();

}
