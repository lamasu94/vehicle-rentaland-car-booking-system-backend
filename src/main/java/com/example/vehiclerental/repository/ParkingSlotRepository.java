
package com.example.vehiclerental.repository;

import com.example.vehiclerental.domain.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, UUID> {
    @Query ("""
        SELECT p from ParkingSlot p
        where not exists (
            select v from Vehicle v where v.parkingSlot = p
        )
        order by p.slotNumber ASC
    """)
    List<ParkingSlot> findAvailableParkingSlots();

}