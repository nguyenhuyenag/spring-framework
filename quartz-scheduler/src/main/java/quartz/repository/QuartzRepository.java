package quartz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quartz.entity.Quartz;

@Repository
public interface QuartzRepository extends JpaRepository<Quartz, Integer> {

}
