package com.example.diana.hotels.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.diana.hotels.model.Hotel;

import java.util.List;

/**
 * Created by dianahas on 12/31/2017.
 */

@Dao
public interface HotelDao {
    @Query("Select * from hotel")
    List<Hotel> getAll();

    @Query("Select * from hotel where hotel.id like :id")
    Hotel getById(int id);

    @Insert
    void add(Hotel... hotels);

    @Delete
    void delete(Hotel... hotel);

    @Update
    void update(Hotel... hotel);
}
