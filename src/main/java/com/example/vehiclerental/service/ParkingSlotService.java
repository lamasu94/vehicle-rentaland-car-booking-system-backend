package com.example.vehiclerental.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.vehiclerental.domain.ParkingSlot;
import com.example.vehiclerental.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSlotService {
    @Autowired 
    private  ParkingSlotRepository parkingSlotRepository;

    
     public ParkingSlot save(ParkingSlot parkingSlot) {
        return parkingSlotRepository.save(parkingSlot);
    }
    public List<ParkingSlot> findAll() {
        return parkingSlotRepository.findAll();
    }
    public Optional<ParkingSlot> findById(UUID id) {
        return parkingSlotRepository.findById(id);
    }
    public Optional<ParkingSlot> update(UUID id, ParkingSlot newParkingSlot) {

        return parkingSlotRepository.findById(id)
                .map(existingParkingSlot -> {

                    existingParkingSlot.setName(newParkingSlot.getName());
                    existingParkingSlot.setSlotNumber(newParkingSlot.getSlotNumber());
                    return parkingSlotRepository.save(existingParkingSlot);
                });
    }

    public boolean delete(UUID id) {
        if(!parkingSlotRepository.existsById(id)) {
            return false;
        }
        parkingSlotRepository.deleteById(id);
        return true;
    }
}