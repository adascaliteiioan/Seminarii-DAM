package ase.dam.cities.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ase.dam.cities.R;
import ase.dam.cities.data.City;
import ase.dam.cities.data.JsonRead;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemoteCitiesFragment extends Fragment {

    private ListView lvCities;
    private CitiesAdapter citiesAdapter;


    public RemoteCitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_cities, container, false);
        lvCities = view.findViewById(R.id.lv_cities);
        citiesAdapter = new CitiesAdapter(getActivity(), new ArrayList<City>());
        lvCities.setAdapter(citiesAdapter);
        lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), citiesAdapter.getCityList().get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        connect();
        return view;
    }

    private void connect() {
        JsonRead jsonRead = new JsonRead(){
            @Override
            protected void onPostExecute(String s) {
                List<City> cities = parseJson(s);
                citiesAdapter.updateList(cities);
            }
        };
        try {
            jsonRead.execute(new URL("https://cities.api.mocked.io/cities"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
