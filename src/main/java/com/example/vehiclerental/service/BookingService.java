package com.example.vehiclerental.service;

import com.example.vehiclerental.domain.Booking;
import com.example.vehiclerental.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Date;
import java.time.LocalDate;
import com.example.vehiclerental.domain.BookingStatus;
import com.example.vehiclerental.domain.Vehicle;
@Service
public class BookingService {
    @Autowired 
    private BookingRepository bookingRepository;
    public String save(Booking booking ) {
       if (booking.getPickupDate().isBefore(LocalDate.now()))  {
         throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Booking date can't be in the past"
        );
       }
       if(bookingRepository.existsByVehicleAndPickupDateAndStatusNot(booking.getVehicle(), booking.getPickupDate(), BookingStatus.DECLINED)){
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Vehicle is already booked on that date"
        );
       }
       booking.setStatus(BookingStatus.REQUESTED);
       bookingRepository.save(booking);
       return "Booking Saved Successfully";
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

    public String changeBookingStatus( UUID id, BookingStatus status){
        Optional<Booking> getBooking = bookingRepository.findById(id);
        if(getBooking.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "The booking with that id does not exist"
        );
        }
        Booking booking = getBooking.get();
        BookingStatus current = booking.getStatus();
        boolean allowed = false;
        if(current == BookingStatus.REQUESTED && (status == BookingStatus.ONGOING || status == BookingStatus.DECLINED)){
            allowed = true;
        }
        if(current == BookingStatus.ONGOING && (status == BookingStatus.RETURNED || status == BookingStatus.DECLINED)){
            allowed = true;
        }
        if(!allowed){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Cannot change Status from " + current +"to " + status 
        );
        }
        booking.setStatus(status);
        bookingRepository.save(booking);
        return "Booking status updated successfully";
    }
}
