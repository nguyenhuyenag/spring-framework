package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.onetoone.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
