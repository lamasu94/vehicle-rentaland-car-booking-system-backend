
package com.example.vehiclerental.repository;

import com.example.vehiclerental.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}