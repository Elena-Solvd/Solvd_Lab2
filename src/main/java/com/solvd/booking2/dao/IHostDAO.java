package com.solvd.booking2.dao;

import com.solvd.booking2.models.Host;

public interface IHostDAO extends IBaseDAO<Host> {

    Host findHostByByEmail(String email);

    Host findHostByPropertyId(Long propertyId);
}