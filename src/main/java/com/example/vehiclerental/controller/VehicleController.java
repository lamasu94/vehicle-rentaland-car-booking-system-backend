
package com.example.vehiclerental.controller;

import com.example.vehiclerental.domain.Vehicle;
import com.example.vehiclerental.service.VehicleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Vehicle vehicle) {

        vehicleService.save(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Vehicle saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> findAll() {

        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        return vehicleService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Vehicle vehicle) {

        return vehicleService.update(id, vehicle)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!vehicleService.delete(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}
