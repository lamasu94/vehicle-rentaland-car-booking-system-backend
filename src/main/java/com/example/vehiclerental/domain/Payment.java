package com.example.vehiclerental.domain;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table(name = "payment")
public class Payment {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;
    private String method;
    private String reference;
    private int amount;
    private LocalDate paymentDate;
    @ManyToOne 
    @JoinColumn (name = "booking_id")
    private Booking booking;

    public Payment(){

    }
    public Payment (String method, String reference, int amount, LocalDate paymentDate){
        this.method = method;
        this.reference = reference;
        this.amount= amount;
        this.paymentDate = paymentDate;

    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
       this.method = method;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
    
}
