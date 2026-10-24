package com.example.vehiclerental.controller;

import com.example.vehiclerental.domain.ParkingSlot;
import com.example.vehiclerental.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parking-slots")
public class ParkingSlotController {
    @Autowired 
    private ParkingSlotService parkingSlotService;
    @PostMapping ("/save")
    public ResponseEntity<String> Save(@RequestBody ParkingSlot parkingSlot){
        parkingSlotService.save(parkingSlot);
        return ResponseEntity.status(HttpStatus.CREATED).body("Parking slot saved successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<ParkingSlot>> findAll(){
       return ResponseEntity.ok(parkingSlotService.findAll());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        return parkingSlotService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody ParkingSlot parkingSlot) {

        return parkingSlotService.update(id, parkingSlot)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        boolean deleted = parkingSlotService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Parking slot deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

