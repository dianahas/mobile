package com.example.diana.hotels.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.diana.hotels.model.User;

import java.util.List;

/**
 * Created by dianahas on 12/31/2017.
 */

@Dao
public interface UserDao {
    @Query("Select * from user")
    List<User> getAll();

    @Insert
    void add(User... users);

    @Query("Select * from user where user.email like :email")
    User findUser(String email);
}
