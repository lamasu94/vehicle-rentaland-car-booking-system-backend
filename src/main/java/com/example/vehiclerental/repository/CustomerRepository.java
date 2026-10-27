
package com.example.vehiclerental.repository;
import org.springframework.data.repository.query.Param;
import com.example.vehiclerental.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query ("""
      select distinct c from Customer c 
      join Booking b on b.customer = c
      where b.vehicle.id = :id
     
    
    """)
    List<Customer> findCustomerByVehicleId(@Param("id") UUID id);
}