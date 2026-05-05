package com.solvd.booking2.models;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "property")
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {
    @XmlAttribute
    private Long id;
    @XmlElement
    private String title;
    @XmlElement
    private PropertyType propertyType;
    @XmlElement
    private Float pricePerNight;
    @XmlElement
    private Integer maxGuests;
    @XmlElement
    private Address address;
    @XmlElement
    private Host host;
    @XmlElement
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







