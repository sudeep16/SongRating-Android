package com.user.songratingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.user.songratingsystem.R;
import com.user.songratingsystem.activities.Register.RegistrationActivity;
import com.user.songratingsystem.bll.LoginBll;
import com.user.songratingsystem.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {
    TextView register;
    EditText userName, passWord;
    Button btnLogin, btnCancel;
    String Username, Password;
    CheckBox remember;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.regUser);
        SpannableString content = new SpannableString("Join Now");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        register.setText(content);


        gyroSense();

        userName = findViewById(R.id.name);
        passWord = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        remember = findViewById(R.id.checkBox);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = userName.getText().toString().trim();
                Password = passWord.getText().toString().trim();

                if (!TextUtils.isEmpty(Username) && !TextUtils.isEmpty(Password)) {
                    login();

                } else {
                    if (TextUtils.isEmpty(Username)) {
                        userName.setError("Enter Username");
                    }
                    if (TextUtils.isEmpty(Password)) {
                        passWord.setError("Enter Password");
                    }
                    return;
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login() {
        LoginBll loginBll = new LoginBll();

        StrictModeClass.StrictMode();
        if (loginBll.checkUser(Username, Password)) {
            if (remember.isChecked()) {
                SaveToShared();
            }
            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }

    }

    private void SaveToShared() {

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Username", Username);
        editor.putString("Password", Password);
        editor.commit();
    }

    private void gyroSense() {
        sensorManager = (SensorManager) getSystemService((SENSOR_SERVICE));
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener gyroSensor = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1] < 0) {
                    Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                } else if (event.values[1] > 0){
                    Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null) {
            sensorManager.registerListener(gyroSensor, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "no sensor found", Toast.LENGTH_SHORT).show();
        }
    }




    }


