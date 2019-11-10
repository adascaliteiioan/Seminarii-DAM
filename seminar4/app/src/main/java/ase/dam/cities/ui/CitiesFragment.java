package ase.dam.cities.ui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ase.dam.cities.MainActivity;
import ase.dam.cities.R;
import ase.dam.cities.data.City;

public class CitiesFragment extends Fragment {

    private FloatingActionButton btnAdd;
    private ListView citiesListView;
    private List<City> cities = new ArrayList<>();
    private CitiesAdapter citiesAdapter;

    public CitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddCityActivity.class);
                startActivityForResult(intent, MainActivity.ADD_CITY_CODE);
            }
        });
        citiesListView = view.findViewById(R.id.lv_cities);
        citiesAdapter = new CitiesAdapter(getActivity(), cities);
        citiesListView.setAdapter(citiesAdapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && data != null) {
            if(requestCode == MainActivity.ADD_CITY_CODE) {
                City city = data.getParcelableExtra("city");
                cities.add(city);
                citiesAdapter.updateCities(cities);
            }
        }
    }
}
