public class Property {
    private Long id;
    private String title;
    private PropertyType propertyType;
    private Float pricePerNight;
    private Long maxGuests;
    private Long addressId;
    private HostId hostId;

    public Property() {}

    public Property(Long id, String title, PropertyType propertyType, Float pricePerNight, Long maxGuests, Long addressId, HostId hostId) {
        this.id = id;
        this.title = title;
        this.propertyType = propertyType;
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
        this.addressId = addressId;
        this.hostId = hostId;
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

    public Long getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Long maxGuests) {
        this.maxGuests = maxGuests;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public HostId getHostId() {
        return hostId;
    }

    public void setHostId(HostId hostId) {
        this.hostId = hostId;
    }
}







