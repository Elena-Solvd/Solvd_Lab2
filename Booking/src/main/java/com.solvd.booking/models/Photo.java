public class Photo {
    private Long id;
    private String url;
    private Property property;

    public Photo() {}

    public Photo(Long id, String url, Property property) {
        this.id = id;
        this.url = url;
        this.property = property;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}