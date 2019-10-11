package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.manytoone.Company;

@Repository
public interface CompanyRepositoty extends JpaRepository<Company, Integer> {

}
