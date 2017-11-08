package com.example.diana.hotels.model;

/**
 * Created by Diana on 07-Nov-17.
 */

public class Hotel {
    private static Integer hotelId = 0;
    private String name;
    private String location;

    public Hotel(String name, String location) {
        hotelId ++;
        this.name = name;
        this.location = location;
    }

    public Hotel() {
        hotelId ++;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
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
