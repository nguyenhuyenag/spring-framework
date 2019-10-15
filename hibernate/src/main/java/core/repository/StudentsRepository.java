package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.onetoone.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {

}
