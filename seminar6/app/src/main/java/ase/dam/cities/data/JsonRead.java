package ase.dam.cities.data;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonRead extends AsyncTask<URL, Void, String> {
    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) urls[0].openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            inputStreamReader.close();
            inputStream.close();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    public List<City> parseJson(String json) {
        List<City> cities = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray citiesArray = jsonObject.getJSONArray("cities");
            for(int i = 0; i< citiesArray.length(); i++) {
                JSONObject cityObject = citiesArray.getJSONObject(i);
                City city = new City();
                city.setName(cityObject.getString("name"));
                city.setCountry(cityObject.getString("country"));
                city.setRegion(cityObject.getString("region"));
                city.setTime(cityObject.getString("date"));
                cities.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
