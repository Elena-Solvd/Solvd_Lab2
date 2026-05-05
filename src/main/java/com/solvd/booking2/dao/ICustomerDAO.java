package com.solvd.booking2.dao;

import com.solvd.booking2.models.Customer;

import java.util.List;

public interface ICustomerDAO extends IBaseDAO<Customer> {

Customer findCustomerByEmail(String email);
Customer findCustomerByPhoneNumber(String phoneNumber);
List<Customer> findCustomersByBookingId(Long bookingId);
}