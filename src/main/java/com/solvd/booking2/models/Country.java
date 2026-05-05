package com.solvd.booking2.models;

public class Country {
    private Long id;
    private Integer code;
    private String name;
    private Integer callingCode;

    public Country() {}

    public Country(Long id, Integer code, String name, Integer callingCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.callingCode = callingCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(Integer callingCode) {
        this.callingCode = callingCode;
    }
}