package com.example.diana.hotels.model;

/**
 * Created by Diana on 07-Nov-17.
 */

public class Hotel {
    private Integer id = 0;
    private String name;
    private String location;

    public Hotel(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
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
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
