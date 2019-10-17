package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.cascade.Companys;

@Repository
public interface CompanysRepositoty extends JpaRepository<Companys, Integer> {

}
