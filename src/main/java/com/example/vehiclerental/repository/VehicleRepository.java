package com.example.vehiclerental.repository;

import com.example.vehiclerental.domain.ParkingSlot;
import com.example.vehiclerental.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
   @Query("""
    SELECT v
    FROM Vehicle v
    JOIN v.features f
    WHERE LOWER(f.name) = LOWER(:name)
""")
List<Vehicle> findVehiclesByFeatureName(@Param("name") String name);
boolean existsByParkingSlot(ParkingSlot parkingSlot);
}