package com.mehmetomurerdolu.booking.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mehmetomurerdolu.booking.entity.EstateEntity;

import java.util.List;

@Dao
public interface EstateDao {

    @Query("SELECT * FROM EstateEntity")
    List<EstateEntity> getAll();

    @Insert
    void insert(EstateEntity estate);

    @Delete
    void delete(EstateEntity estate);

    @Update
    void update(EstateEntity estate);

}
