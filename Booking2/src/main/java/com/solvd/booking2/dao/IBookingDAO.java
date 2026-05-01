package com.solvd.booking2.dao;

import com.solvd.booking2.models.Booking;

import java.util.List;

public interface IBookingDAO extends IBaseDAO<Booking> {

    List<Booking> findBookingsByPropertyId(Long propertyId);
    List <Booking> findBookingsByHostId(Long Id);

}