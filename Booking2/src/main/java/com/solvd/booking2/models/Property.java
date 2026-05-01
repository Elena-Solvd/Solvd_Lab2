package com.solvd.booking2.models;

import java.util.List;

public class Property {
    private Long id;
    private String title;
    private PropertyType propertyType;
    private Float pricePerNight;
    private Integer maxGuests;
    private Address address;
    private Host host;
    private List<Photo> photos;

    public Property() {}

    public Property(Long id, String title, PropertyType propertyType, Float pricePerNight, Integer maxGuests, Address address, Host host) {
        this.id = id;
        this.title = title;
        this.propertyType = propertyType;
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
        this.address = address;
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public Float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}







