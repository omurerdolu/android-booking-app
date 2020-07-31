package com.mehmetomurerdolu.booking.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer userId;

    @ColumnInfo(name = "fullname")
    private String fullname;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "mail")
    private String mail;

    @ColumnInfo(name = "password")
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
