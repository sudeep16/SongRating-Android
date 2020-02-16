package com.example.songratingwearable;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.songratingwearable.bll.LoginBll;
import com.example.songratingwearable.strictmode.StrictModeClass;

public class MainActivity extends WearableActivity {

    EditText name, pass;
    Button btnLogin;
    String Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = name.getText().toString().trim();
                Password = pass.getText().toString().trim();

                if (!TextUtils.isEmpty(Username) && !TextUtils.isEmpty(Password)) {
                    login();

                } else {
                    if (TextUtils.isEmpty(Username)) {
                        name.setError("Enter Username");
                    }
                    if (TextUtils.isEmpty(Password)) {
                        pass.setError("Enter Password");
                    }
                    return;
                }
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }

    private void login() {
        LoginBll loginBll = new LoginBll();

        StrictModeClass.StrictMode();
        if (loginBll.checkUser(Username, Password)) {
            Toast.makeText(MainActivity.this, "Welcome " + Username, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }

    }
}
