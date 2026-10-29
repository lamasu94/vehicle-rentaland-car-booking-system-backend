package com.example.vehiclerental.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

import com.example.vehiclerental.domain.Payment;
import com.example.vehiclerental.service.PaymentService;


@RestController 
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired 
    private PaymentService paymentService;
    @PostMapping("/save")
    public ResponseEntity<String> savePayments(@RequestParam  UUID bookingId, @RequestBody Payment payment){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.savePayment(bookingId, payment));
    }
   @GetMapping("/customer/{drivingLicense}")
   public ResponseEntity<List<Payment>> findPaymentByCustomerLicense(@PathVariable  String drivingLicense){
    return ResponseEntity.ok(paymentService.findPaymentByCustomerLicense(drivingLicense));
   }
   @DeleteMapping("delete/{id}")
   public ResponseEntity<String> deletePayment(@PathVariable UUID id){
    return ResponseEntity.ok(paymentService.deletePayment(id));
   }
    
}
