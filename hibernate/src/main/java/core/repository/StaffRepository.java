package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.manytoone.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
