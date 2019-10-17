package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entity.enumerated.Programing;

public interface ProgramingRepositoty extends JpaRepository<Programing, Integer> {

}
