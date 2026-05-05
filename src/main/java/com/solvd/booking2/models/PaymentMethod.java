package com.solvd.booking2.models;

public class PaymentMethod {

    private Long id;
    private PaymentType paymentType;
    private Boolean isActive;

    public PaymentMethod() {
    }

    public PaymentMethod(Long id, PaymentType paymentType, Boolean isActive) {
        this.id = id;
        this.paymentType = paymentType;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
