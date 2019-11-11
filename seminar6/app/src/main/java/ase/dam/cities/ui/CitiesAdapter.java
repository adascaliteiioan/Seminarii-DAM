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
    private List<City> cityList;
    private LayoutInflater inflater;

    public CitiesAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cityList = cities;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public City getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.item_city, viewGroup, false);
        TextView city = rowView.findViewById(R.id.et_city_name);
        TextView country = rowView.findViewById(R.id.et_country);
        city.setText(cityList.get(i).getName());
        country.setText(cityList.get(i).getCountry());
        return rowView;
    }

    public void updateList(List<City> newCities) {
        cityList = newCities;
        notifyDataSetChanged();
    }

    public List<City> getCityList() {
        return cityList;
    }
}
