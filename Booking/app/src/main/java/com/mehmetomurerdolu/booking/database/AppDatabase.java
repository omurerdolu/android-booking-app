package com.mehmetomurerdolu.booking.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mehmetomurerdolu.booking.dao.EstateDao;
import com.mehmetomurerdolu.booking.dao.UserDao;
import com.mehmetomurerdolu.booking.entity.EstateEntity;
import com.mehmetomurerdolu.booking.entity.UserEntity;

@Database(entities = {UserEntity.class, EstateEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract EstateDao estateDao();
}
