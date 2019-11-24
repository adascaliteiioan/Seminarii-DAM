package com.ai.seminar8.data.db;

import android.content.Context;

import androidx.room.Room;

public class DataBase {

    private static DataBase instance;
    private RoomDB database;

    private DataBase(Context context) {
        database = Room.databaseBuilder(context, RoomDB.class, "cities_db").build();
    }

    public static DataBase getInstance(Context context) {
        if (instance == null) {
            instance = new DataBase(context);
        }
        return instance;
    }

    public RoomDB getDatabase() {
        return database;
    }
}
