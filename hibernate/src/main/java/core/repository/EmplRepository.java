package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.manytoone.ecollection.Empl;

@Repository
public interface EmplRepository extends JpaRepository<Empl, Integer> {

}
