package com.study.model;

public class County {
    private Integer id;

    private String name;

    private String weatherId;

    public County(Integer id, String name, String weatherId) {
        this.id = id;
        this.name = name;
        this.weatherId = weatherId;
    }

    public County() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId == null ? null : weatherId.trim();
    }
}