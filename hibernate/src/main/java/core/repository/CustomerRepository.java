package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entity.createupdatetime.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
