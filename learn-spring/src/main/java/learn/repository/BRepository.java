package learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learn.entity.TableB;

@Repository
public interface BRepository extends JpaRepository<TableB, Integer> {

}
