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

        userName = findViewById(R.id.name);
        passWord = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        remember = findViewById(R.id.checkBox);

        proximity();

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
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
                WindowManager.LayoutParams params = LoginActivity.this.getWindow().getAttributes();
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0) {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = 0;
                        getWindow().setAttributes(params);
                    } else {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = -1f;
                        getWindow().setAttributes(params);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(proximityListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }


    }


