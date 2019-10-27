package ase.dam.cities.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ase.dam.cities.R;
import ase.dam.cities.data.City;

public class AddCityActivity extends AppCompatActivity {

    private EditText etCityName;
    private EditText etCountry;
    private Spinner spRegion;
    private DatePicker datePicker;
    private Button btnSave;
    private City currentCity = new City();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        initViews();
        //metoda alternativa da a seta valori unui spinner
        //addSpinnerItems();
    }

    private void initViews() {
        etCityName = findViewById(R.id.et_city_name);
        etCountry = findViewById(R.id.et_country);
        spRegion = findViewById(R.id.sp_country_region);
        datePicker = findViewById(R.id.dp_time_selector);
        btnSave = findViewById(R.id.btn_save);
    }

    private void addSpinnerItems() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        spRegion.setAdapter(adapter);
    }

}
