package com.ai.seminar8.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class City {
    @PrimaryKey(autoGenerate = true)
    public int cityId;
    @ColumnInfo(name = "city_name")
    public String cityName;
    @ColumnInfo(name = "country_name")
    public String countryName;

    @NonNull
    @Override
    public String toString() {
        return cityId + " " + cityName;
    }
}
