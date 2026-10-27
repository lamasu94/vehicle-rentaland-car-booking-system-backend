
package com.example.vehiclerental.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.vehiclerental.domain.Customer;
import com.example.vehiclerental.repository.CustomerRepository;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired 
    private CustomerRepository customerRepository;
    @Autowired 
    private VehicleRepository vehicleRepo;
    
    
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
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
    public List<Customer> findCustomerByVehicleId(UUID vehicleId) {
       if (vehicleRepo.findById(vehicleId).isEmpty()){
         throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "The vehicle with that id does not exist"
        );
       }
        return customerRepository.findCustomerByVehicleId(vehicleId);
    }
}