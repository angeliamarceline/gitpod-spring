package id.example.javaSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.example.javaSpring.model.entity.Customer;

// The interface extends JpaRepository that has the CRUD operation methods
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
