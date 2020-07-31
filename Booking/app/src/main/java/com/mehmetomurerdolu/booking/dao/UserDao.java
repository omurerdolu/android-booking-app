package com.mehmetomurerdolu.booking.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mehmetomurerdolu.booking.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserEntity")
    List<UserEntity> findAll();

    @Query("SELECT * FROM UserEntity Where mail ==:mail AND password == :password")
    UserEntity findByMailAndPassword(String mail, String password);

    @Query("SELECT * FROM UserEntity Where mail == :mail")
    UserEntity findByMail(String mail);

    @Insert
    void insert(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Update
    void update(UserEntity user);

}
