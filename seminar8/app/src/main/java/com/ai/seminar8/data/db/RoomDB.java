package com.ai.seminar8.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ai.seminar8.data.City;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    public abstract CityDAO cityDAO();

}
