package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.manytoone.ecollection.EmplPosition;

@Repository
public interface EmplPositionRepository extends JpaRepository<EmplPosition, Integer> {

}
