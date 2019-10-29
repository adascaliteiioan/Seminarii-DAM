package ase.dam.cities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import ase.dam.cities.data.City;
import ase.dam.cities.ui.AddCityActivity;
import ase.dam.cities.ui.CitiesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final Integer ADD_CITY_REQUEST_CODE = 100;
    private List<City> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddCityActivity.class), ADD_CITY_REQUEST_CODE);
            }
        });
    }

    private void configNavigation() {
        //initializare toolbar - bara de actiune
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initializare drawer layout - panou meniu lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        //legare meniu lateral cu bara actiune
        // + eveniment de deschidere
        //creare instanta utilitara
        ActionBarDrawerToggle actionBar =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        //atasare eveniment
        drawerLayout.addDrawerListener(actionBar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //sincronizare actionBartoggle
        actionBar.syncState();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == ADD_CITY_REQUEST_CODE) {
            if (data == null) return;
            City city = data.getParcelableExtra("city");
            cities.add(city);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home: {
                changeFragment(new CitiesFragment());
                break;
            }
            case R.id.nav_remote: {

            }
            case R.id.nav_about: {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
