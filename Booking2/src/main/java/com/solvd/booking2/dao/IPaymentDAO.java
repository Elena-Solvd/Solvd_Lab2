package com.solvd.booking2.dao;

import com.solvd.booking2.models.Payment;
import com.solvd.booking2.models.PaymentStatus;

import java.util.List;

public interface IPaymentDAO extends IBaseDAO<Payment> {

    Payment findPaymentByCardId(Long cardId);
    List<Payment> findPaymentsByCustomerId(Long customerId);
    List <Payment> findPaymentsByPaymentStatus(PaymentStatus paymentStatus);
}