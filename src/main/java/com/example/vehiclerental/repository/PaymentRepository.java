package com.example.vehiclerental.repository;
import org.springframework.data.repository.query.Param;

import com.example.vehiclerental.domain.Booking;
import com.example.vehiclerental.domain.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query 
    ("""
        select p from Payment p where p.booking.customer.drivingLicense = :drivingLicense
        order By p.paymentDate DESC
    """)
    List <Payment> findPaymentsByCustomerDrivingLicense(@Param("drivingLicense") String drivingLicense);
    boolean existsByBooking(Booking booking);
}
