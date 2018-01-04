package com.example.diana.hotels.services;

import com.example.diana.hotels.model.Hotel;
import com.example.diana.hotels.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/login")
    Call<User> login(@Body User user);

    @GET("/api/hotels")
    Call<List<Hotel>> getHotels();

    @POST("/api/hotels")
    Call<Hotel> addHotel(@Body Hotel hotel);

    @DELETE("/api/hotel/{hotelId}")
    Call<Hotel> delete(@Path("hotelId") int hotelId);

    @POST("/api/hotel/{hotelId}")
    Call<Hotel> update(@Path("hotelId") int hotelId, @Body Hotel hotel);
}