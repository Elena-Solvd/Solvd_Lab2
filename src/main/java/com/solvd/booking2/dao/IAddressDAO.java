package com.solvd.booking2.dao;

import com.solvd.booking2.models.Address;

import java.util.List;

public interface IAddressDAO extends IBaseDAO<Address> {

    List<Address> findAddressesByCountryId(Long countryId);
    Address findAddressByPropertyId (String propertyId);
}