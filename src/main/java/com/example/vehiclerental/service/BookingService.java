package com.example.vehiclerental.service;

import com.example.vehiclerental.domain.Booking;
import com.example.vehiclerental.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Date;
import java.time.LocalDate;
import com.example.vehiclerental.domain.BookingStatus;
@Service
public class BookingService {
    @Autowired 
    private BookingRepository bookingRepository;
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
    public Optional<Booking> findById(UUID id) {
        return bookingRepository.findById(id);
    }
    public Optional<Booking> update(UUID id, Booking newBooking) {
        return bookingRepository.findById(id)
                .map(existingBooking -> {
                    existingBooking.setPickupDate(newBooking.getPickupDate());
                    existingBooking.setStatus(newBooking.getStatus());
                    existingBooking.setDays(newBooking.getDays());
                    existingBooking.setTotalAmount(newBooking.getTotalAmount());
                    existingBooking.setCustomer(newBooking.getCustomer());
                    existingBooking.setVehicle(newBooking.getVehicle());
                    return bookingRepository.save(existingBooking);
                });
    }
    public boolean delete(UUID id) {
        if(!bookingRepository.existsById(id)) {
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }
    public List<Booking> findByStatus(BookingStatus status) {
       
        return bookingRepository.findByStatusOrderByPickupDateAsc(status);
    }
    public List<Booking> findByPickupDateBetween( LocalDate startDate, LocalDate endDate) {
        
        return bookingRepository.findByPickupDateBetweenOrderByPickupDateAsc(startDate, endDate);
    }
}
