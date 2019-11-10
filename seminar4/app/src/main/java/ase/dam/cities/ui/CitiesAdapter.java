package ase.dam.cities.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ase.dam.cities.R;
import ase.dam.cities.data.City;

public class CitiesAdapter extends BaseAdapter {

    private Context context;
    private List<City> cities;
    private LayoutInflater inflater;

    public CitiesAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public City getItem(int i) {
        return cities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.item_city,viewGroup, false);
        TextView tvCityName = rowView.findViewById(R.id.et_city_name);
        TextView tvCountry = rowView.findViewById(R.id.et_country);
        City currentCity = cities.get(position);
        tvCityName.setText(currentCity.getName());
        tvCountry.setText(currentCity.getCountry());
        return rowView;
    }

    public void updateCities(List<City> newCities) {
        this.cities = newCities;
        notifyDataSetChanged();
    }
}
