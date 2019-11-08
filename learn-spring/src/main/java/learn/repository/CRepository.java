package learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learn.entity.TableC;

@Repository
public interface CRepository extends JpaRepository<TableC, Integer> {

}
