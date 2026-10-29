package com.example.vehiclerental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.vehiclerental.domain.Booking;
import com.example.vehiclerental.domain.BookingStatus;
import com.example.vehiclerental.domain.Payment;
import com.example.vehiclerental.repository.BookingRepository;
import com.example.vehiclerental.repository.PaymentRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class PaymentService {
        @Autowired
        private PaymentRepository paymentRepo;
        @Autowired
        private BookingRepository bookingRepo;

        public String savePayment(UUID bookingId, Payment payment) {
                Optional<Booking> getBooking = bookingRepo.findById(bookingId);
                if (getBooking.isEmpty()) {
                        throw new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "The booking with that id does not exist");
                }
                Booking booking = getBooking.get();
                BookingStatus bookingStatus = booking.getStatus();
                if (bookingStatus != BookingStatus.RETURNED) {
                        throw new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Payments are only for RETURNED bookings");
                }
                payment.setBooking(booking);
                payment.setPaymentDate(LocalDate.now());
                paymentRepo.save(payment);
                return "Payment saved successfully";
        }

        public List<Payment> findPaymentByCustomerLicense(String drivingLicense) {
                return paymentRepo.findPaymentsByCustomerDrivingLicense(drivingLicense);
        }
        public String deletePayment (UUID id){
                if(!paymentRepo.existsById(id)){
                        throw new ResponseStatusException(
                                 HttpStatus.NOT_FOUND,
                                "The payment with that id does not exist"); 
                }
                paymentRepo.deleteById(id);
                return "Payment deleted Successfully";
        }
}
