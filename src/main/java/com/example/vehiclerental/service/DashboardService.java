package com.example.vehiclerental.service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehiclerental.domain.BookingStatus;
import com.example.vehiclerental.repository.BookingRepository;
import com.example.vehiclerental.repository.CustomerRepository;
import com.example.vehiclerental.repository.VehicleRepository;
import com.example.vehiclerental.repository.ParkingSlotRepository;
import com.example.vehiclerental.repository.PaymentRepository;

@Service 
public class DashboardService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Map<String, Long> getDashboardSummary(){
        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("customers", customerRepository.count());

        summary.put("vehicles", vehicleRepository.count());

        summary.put("freeParkingSlots",
                (long) parkingSlotRepository.findAvailableParkingSlots().size());
        summary.put("bookingToday", bookingRepository.countByPickupDate(LocalDate.now()));
        summary.put("requested", bookingRepository.countByStatus(BookingStatus.REQUESTED));
        summary.put("payments", paymentRepository.count());
        return summary;
    }
}
