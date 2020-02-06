package com.user.songratingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.user.songratingsystem.R;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkPermission();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
            }
        }, 2000);
    }

    private void checkUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");
        String password = sharedPreferences.getString("Password", "");

        if(username.equals("admin") && password.equals("admin")){

            Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Welcome to Dahboard", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private  void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.CAMERA
                    },0);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },0);
        }

    }
}
