package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.manytoone.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

}
