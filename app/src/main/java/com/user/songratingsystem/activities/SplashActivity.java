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
import com.user.songratingsystem.bll.LoginBll;
import com.user.songratingsystem.strictmode.StrictModeClass;


public class SplashActivity extends AppCompatActivity {

    String username, password;
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

    public void checkUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        username = sharedPreferences.getString("Username", null);
        password = sharedPreferences.getString("Password", null);

        if(username != null && password != null){
            login();
            Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }

        else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login() {

        String Username = username;
        String Password = password;

        LoginBll loginBll = new LoginBll();

        StrictModeClass.StrictMode();
        if (loginBll.checkUser(Username, Password)) {
            Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
            startActivity(intent);
        }

        else {
            Toast.makeText(SplashActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
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
