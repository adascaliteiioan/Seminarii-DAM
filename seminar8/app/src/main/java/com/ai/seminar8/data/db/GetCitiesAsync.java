package com.ai.seminar8.data.db;

import android.content.Context;
import android.os.AsyncTask;

import com.ai.seminar8.data.City;

import java.util.List;

public class GetCitiesAsync extends AsyncTask<Void, Void, List<City>> {

    private Context context;

    public GetCitiesAsync(Context context) {
        this.context = context;
    }

    @Override
    protected List<City> doInBackground(Void... voids) {

        return DataBase.getInstance(context).getDatabase().cityDAO().getAllCities();
    }
}
