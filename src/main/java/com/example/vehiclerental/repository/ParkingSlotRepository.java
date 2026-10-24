
package com.example.vehiclerental.repository;

import com.example.vehiclerental.domain.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, UUID> {
}