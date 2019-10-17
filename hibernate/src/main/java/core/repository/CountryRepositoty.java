package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.cascade.Country;

@Repository
public interface CountryRepositoty extends JpaRepository<Country, Integer> {

}
