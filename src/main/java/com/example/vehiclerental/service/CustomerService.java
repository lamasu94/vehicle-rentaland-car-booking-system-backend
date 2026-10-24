
package com.example.vehiclerental.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.vehiclerental.domain.Customer;
import com.example.vehiclerental.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired 
    private CustomerRepository customerRepository;
    
    
     public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }
    public Optional<Customer> update(UUID id, Customer newCustomer) {

        return customerRepository.findById(id)
                .map(existingCustomer -> {

                    existingCustomer.setFirstName(newCustomer.getFirstName());
                    existingCustomer.setLastName(newCustomer.getLastName());
                    existingCustomer.setDrivingLicense(newCustomer.getDrivingLicense());
                    existingCustomer.setEmail(newCustomer.getEmail());
                    existingCustomer.setPhone(newCustomer.getPhone());

                    return customerRepository.save(existingCustomer);
                });
    }
    public boolean delete(UUID id) {
        if(!customerRepository.existsById(id)) {
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }
}
