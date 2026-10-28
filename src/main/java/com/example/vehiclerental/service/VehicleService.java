package com.example.vehiclerental.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.vehiclerental.domain.Vehicle;
import com.example.vehiclerental.repository.FeatureRepository;
import com.example.vehiclerental.repository.ParkingSlotRepository;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.vehiclerental.domain.Feature;
import com.example.vehiclerental.domain.ParkingSlot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired 
    private FeatureRepository featureRepository;
    @Autowired 
    private  ParkingSlotRepository parkingRepo;
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
    public List<Vehicle> findVehiclesByFeatureName(String name) {
        return vehicleRepository.findVehiclesByFeatureName(name);
    }
    public String addFeatureToVehicle (UUID vehicleId, UUID featureId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The vehicle with that id does not exist"
            ));
        Feature feature =featureRepository.findById(featureId).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The vehicle with that id does not exist"
            ));
        if(vehicle.getFeatures().contains(feature)){
             throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "The vehicle already has that feature"
        );
        }
        vehicle.getFeatures().add(feature);
        vehicleRepository.save(vehicle);
        return "Feature added to vehicle";
    }
    public String changeParkingSlot (UUID vehicleId, int slotNumber){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The vehicle with that id does not exist"
            ));
        ParkingSlot parkingSlot = parkingRepo.findBySlotNumber(slotNumber).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The parking slot with that slotNumber does not exist"
            ));
        if (vehicleRepository.existsByParkingSlot(parkingSlot)
            && !parkingSlot.equals(vehicle.getParkingSlot())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "That parking slot is taken by another vehicle"
            );
        }
        vehicle.setParkingSlot(parkingSlot);
        vehicleRepository.save(vehicle);
        return "Parking slot changed Successfully";
    }
}
