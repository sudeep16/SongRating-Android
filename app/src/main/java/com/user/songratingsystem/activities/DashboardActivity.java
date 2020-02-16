package com.user.songratingsystem.activities;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.user.songratingsystem.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class DashboardActivity extends AppCompatActivity {

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_rated_playlilst,  R.id.navigation_aboutus)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        proximity();

    }


    public void proximity() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensor == null) {
            Toast.makeText(this, "No Sensor Detected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sensor Kicking In ..... ", Toast.LENGTH_SHORT).show();
        }

        SensorEventListener proximityListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <= 1) {
                        Intent home = new Intent(Intent.ACTION_MAIN);
                        home.addCategory(Intent.CATEGORY_HOME);
                        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(home);
                    }
                }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(proximityListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
}
