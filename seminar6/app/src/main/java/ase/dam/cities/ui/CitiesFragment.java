package ase.dam.cities.ui;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ase.dam.cities.R;
import ase.dam.cities.data.City;

import static ase.dam.cities.MainActivity.ADD_CITY_REQUEST_CODE;

public class CitiesFragment extends Fragment {

    private ListView listView;
    private List<City> cities = new ArrayList<>();
    private CitiesAdapter citiesAdapter;

    public CitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        listView = view.findViewById(R.id.lv_cities);
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), AddCityActivity.class), ADD_CITY_REQUEST_CODE);
            }
        });
        citiesAdapter = new CitiesAdapter(getActivity(), cities);
        listView.setAdapter(citiesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        citiesAdapter.updateList(cities);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == ADD_CITY_REQUEST_CODE) {
            if (data == null) return;
            City city = data.getParcelableExtra("city");
            cities.add(city);
            citiesAdapter.updateList(cities);
        }
    }



}
