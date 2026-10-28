
package com.example.vehiclerental.controller;

import com.example.vehiclerental.domain.Booking;
import com.example.vehiclerental.service.BookingService;
import com.example.vehiclerental.domain.BookingStatus;
import com.example.vehiclerental.domain.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired 
    private  BookingService bookingService;

    
    
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Booking booking) {

         return ResponseEntity.ok(bookingService.save(booking));
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<Booking>> findAll() {

        return ResponseEntity.ok(bookingService.findAll());
    }

    
   @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        var booking = bookingService.findById(id);

        if (booking.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The booking with that id does not exist");
        }

        return ResponseEntity.ok(booking.get());
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Booking booking) {

        var updatedBooking = bookingService.update(id, booking);

        if (updatedBooking.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The booking with that id does not exist");
        }

        return ResponseEntity.ok(updatedBooking.get());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        if (!bookingService.delete(id)) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The booking with that id does not exist");
        }

        return ResponseEntity.ok("Booking deleted successfully");
    }
    @GetMapping("by-status")
    public ResponseEntity<List<Booking>> findByStatus(@RequestParam String status) {
        List<Booking> bookings = bookingService.findByStatus(BookingStatus.valueOf(status.trim().toUpperCase()));
        return ResponseEntity.ok(bookings);
    }
    @GetMapping("between")
    public ResponseEntity<List<Booking>> findByPickupDateBetween(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Booking> bookings = bookingService.findByPickupDateBetween(start, end);
        return ResponseEntity.ok(bookings);
    }
     @PatchMapping ("{id}/status")   
     public ResponseEntity<?> changeBookingStatus (@PathVariable UUID id, @RequestParam BookingStatus status){
        return ResponseEntity.ok(bookingService.changeBookingStatus(id, status));
     }
}
