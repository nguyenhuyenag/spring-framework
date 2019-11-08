package learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learn.entity.TableA;

@Repository
public interface ARepository extends JpaRepository<TableA, Integer> {

}
