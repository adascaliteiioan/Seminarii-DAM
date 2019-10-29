package ase.dam.cities.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import ase.dam.cities.MainActivity;
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
        setupListeners();
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

    private void setupListeners() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                Date d = calendar.getTime();
                SimpleDateFormat dateFormatter = new SimpleDateFormat(
                        "dd-MMM-yyyy hh:mm", Locale.getDefault());
                String strDate = dateFormatter.format(d);
                currentCity.setTime(strDate);
                Toast.makeText(AddCityActivity.this, strDate, Toast.LENGTH_LONG).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    currentCity.setName(etCityName.getText().toString());
                    currentCity.setCountry(etCountry.getText().toString());
                    currentCity.setRegion(String.valueOf(spRegion.getSelectedItem()));
                    Intent intent = new Intent();
                    intent.putExtra("city", currentCity);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private boolean validate() {
        if (etCityName.getText().toString().isEmpty()) {
            etCityName.setError(getString(R.string.add_city_error_no_name));
            return false;
        }

        if (etCountry.getText().toString().isEmpty()) {
            etCountry.setError(getString(R.string.add_city_error_no_country));
            return false;
        }

        if (currentCity.getTime() == null) {
            Toast.makeText(AddCityActivity.this, getString(R.string.add_city_error_no_time), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
