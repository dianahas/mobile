package com.example.diana.hotels.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

/**
 * Created by dianahas on 12/31/2017.
 */

@Entity(indices = {@Index(value = {"email"},
        unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    @Expose
    private int id;

    @ColumnInfo(name = "email")
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private boolean admin;

    public User(String email, String password, boolean admin) {
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
