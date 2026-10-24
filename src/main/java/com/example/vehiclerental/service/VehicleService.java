package com.example.vehiclerental.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.vehiclerental.domain.Vehicle;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    
     public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
    public Optional<Vehicle> findById(UUID id) {
        return vehicleRepository.findById(id);
    }
    public Optional<Vehicle> update(UUID id, Vehicle newVehicle) {

        return vehicleRepository.findById(id)
                .map(existingVehicle -> {

                    existingVehicle.setBrand(newVehicle.getBrand());
                    existingVehicle.setModel(newVehicle.getModel());
                    existingVehicle.setPlateNumber(newVehicle.getPlateNumber());
                    existingVehicle.setPricePerDay(newVehicle.getPricePerDay());
                    existingVehicle.setParkingSlot(newVehicle.getParkingSlot());
                    existingVehicle.setFeatures(newVehicle.getFeatures());
                    return vehicleRepository.save(existingVehicle);
                });
    }
    public boolean delete(UUID id) {
        if(!vehicleRepository.existsById(id)) {
            return false;
        }
        vehicleRepository.deleteById(id);
        return true;
    }
}
