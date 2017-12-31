package com.example.diana.hotels.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.diana.hotels.dao.HotelDao;
import com.example.diana.hotels.dao.UserDao;
import com.example.diana.hotels.model.Hotel;
import com.example.diana.hotels.model.User;

/**
 * Created by Alis on 12/5/2017.
 */
@Database(entities = {User.class, Hotel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract HotelDao hotelDao();
}
