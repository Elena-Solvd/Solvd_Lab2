package com.solvd.booking2.dao;

public interface IPropertyDAO extends IBaseDAO<Property> {

    List <Property> findPropertiesByHostId(Long hostId);
    Property findPropertyByAddressId(Long addressId);

}