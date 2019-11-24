package com.ai.seminar8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ai.seminar8.data.City;
import com.ai.seminar8.data.db.DataBase;
import com.ai.seminar8.data.db.GetCitiesAsync;
import com.ai.seminar8.data.db.InsertCitiesAsync;
import com.ai.seminar8.data.sharedprefs.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBase dataBase;
    private Button btnGetCities;
    private Button btnPrefs;
    private SharedPrefs prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetCities = findViewById(R.id.btn_cities);
        btnPrefs = findViewById(R.id.btn_prefs);
        prefs = SharedPrefs.getInstance(this);
        dataBase = DataBase.getInstance(this);
        saveInPrefs();
        saveCities();
        btnGetCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCitiesAsync(MainActivity.this) {
                    @Override
                    protected void onPostExecute(List<City> cities) {
                        for (int i = 0; i < cities.size(); i++) {
                            Toast.makeText(MainActivity.this, cities.get(i).toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute();
            }
        });

        btnPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = prefs.getValue("int");
                String s = prefs.getString("string");
                Toast.makeText(MainActivity.this, s + " " + v, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveCities() {
        City[] cities = new City[10];
        for (int i = 0; i < 10; i++) {
            City city = new City();
            city.cityName = "Nume " + i;
            city.countryName = "Country " + i;
            cities[i] = city;
        }
        new InsertCitiesAsync(this).execute(cities);
    }

    private void saveInPrefs() {
        prefs.saveInteger("int", 25);
        prefs.saveString("string", "string value");
    }
}
