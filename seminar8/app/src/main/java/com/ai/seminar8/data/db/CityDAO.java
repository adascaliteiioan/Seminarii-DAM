package com.ai.seminar8.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ai.seminar8.data.City;

import java.util.List;

@Dao
public interface CityDAO {

    @Query("SELECT * FROM cities")
    public List<City> getAllCities();

    @Query("SELECT * FROM cities WHERE cityId = :cityId")
    public List<City> getById(int cityId);

    @Insert
    public void insertCity(City city);

    @Delete
    public void deleteCity(City city);
}
