package com.solvd.booking2.dao;

import com.solvd.booking2.models.Country;

public interface ICountryDAO extends IBaseDAO<Country> {

    Country findCountryByCallingCode(String callingCode);

}