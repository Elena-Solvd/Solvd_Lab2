package com.solvd.booking2.models;

public class Host {

    private Long id;
    private Boolean isCompany;
    private String companyName;
    private User user;

    public Host() {
    }

    public Host(Long id, Boolean isCompany, String companyName, User user) {
        this.id = id;
        this.isCompany = isCompany;
        this.companyName = companyName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(Boolean isCompany) {
        this.isCompany = isCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
