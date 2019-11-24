package com.ai.seminar8.data.db;

import android.content.Context;
import android.os.AsyncTask;

import com.ai.seminar8.data.City;

public class InsertCitiesAsync extends AsyncTask<City, Void, Void> {

    private Context context;

    public InsertCitiesAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(City... lists) {
        DataBase dataBase = DataBase.getInstance(context);
        for (int i = 0; i < lists.length; i++) {
            dataBase.getDatabase().cityDAO().insertCity(lists[i]);
        }
        return null;
    }
}
