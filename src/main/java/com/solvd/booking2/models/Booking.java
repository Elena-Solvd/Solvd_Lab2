package com.solvd.booking2.models;

import java.time.LocalDateTime;

public class Booking {

    private Long id;
    private BookingStatus booklingStatus;
    private Float totalPrice;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Property property;
    private Customer custommer;

    public Booking() {
    }

    public Booking(Long id, BookingStatus booklingStatus, Float totalPrice, Property property, Customer custommer) {
        this.id = id;
        this.booklingStatus = booklingStatus;
        this.totalPrice = totalPrice;
        this.property = property;
        this.custommer = custommer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingStatus getBooklingStatus() {
        return booklingStatus;
    }

    public void setBooklingStatus(BookingStatus booklingStatus) {
        this.booklingStatus = booklingStatus;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Customer getCustommer() {
        return custommer;
    }

    public void setCustommer(Customer custommer) {
        this.custommer = custommer;
    }
}
