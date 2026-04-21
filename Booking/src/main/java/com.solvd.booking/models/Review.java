public class Review {

    private Long id;
    private Rating rating;
    private String comment;
    private Property property;
    private Customer customer;

    public Review() {
    }

    public Review(Long id, Rating rating, String comment, Property property, Customer customer) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.property = property;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
