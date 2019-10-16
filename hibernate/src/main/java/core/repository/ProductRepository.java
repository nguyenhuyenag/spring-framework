package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.manytomany.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
