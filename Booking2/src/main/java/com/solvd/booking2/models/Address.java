package com.solvd.booking2.models;

public class Address {
    private Long id;
    private String city;
    private String street;
    private String zipcode;
    private Country country;

    public Address() {
    }

    public Address(Long id, String city, String street, String zipcode, Country country) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}