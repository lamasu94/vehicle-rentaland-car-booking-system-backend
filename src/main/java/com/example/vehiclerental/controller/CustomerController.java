
package com.example.vehiclerental.controller;

import com.example.vehiclerental.domain.Customer;
import com.example.vehiclerental.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Customer customer) {

        customerService.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Customer saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAll() {

        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Customer customer) {

        return customerService.update(id, customer)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!customerService.delete(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Customer deleted successfully");
    }
    @GetMapping("by-last-name")
    public ResponseEntity<List<Customer>> findByLastName(@RequestParam String lastName) {
        List<Customer> customers = customerService.findByLastName(lastName);
        return ResponseEntity.ok(customers);
    }
    @GetMapping("of-vehicle/{vehicleId}")
    public ResponseEntity<List<Customer>> findCustomerByVehicleId(@PathVariable UUID vehicleId) {
        List<Customer> customers = customerService.findCustomerByVehicleId(vehicleId);
        return ResponseEntity.ok(customers);
    }
    
    
}
