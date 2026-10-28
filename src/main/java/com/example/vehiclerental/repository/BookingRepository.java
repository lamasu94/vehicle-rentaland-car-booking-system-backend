package com.example.vehiclerental.repository;
import com.example.vehiclerental.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vehiclerental.domain.BookingStatus;
import com.example.vehiclerental.domain.Vehicle;

import java.util.UUID;
import java.util.List;
import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByStatusOrderByPickupDateAsc(BookingStatus status);
    List<Booking> findByPickupDateBetweenOrderByPickupDateAsc(LocalDate startDate, LocalDate endDate);
    boolean existsByVehicleAndPickupDateAndStatusNot(Vehicle vehicle, LocalDate Date, BookingStatus status);
}
