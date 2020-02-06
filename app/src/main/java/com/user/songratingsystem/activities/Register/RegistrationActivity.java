package com.user.songratingsystem.activities.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.user.songratingsystem.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText uName, pass, cPass, email, phone, address;
    RadioButton male, female, others;
    Button btnContinue, cancelBtn;
    public static String Username, Password, Cpassword, Email, Address, Phone, Gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        uName = findViewById(R.id.uName);
        pass = findViewById(R.id.pass);
        cPass = findViewById(R.id.cPass);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        others = findViewById(R.id.others);

        btnContinue = findViewById(R.id.btnContinue);
        cancelBtn = findViewById(R.id.cancelBtn);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Username = uName.getText().toString().trim();
                Password = pass.getText().toString().trim();
                Cpassword = cPass.getText().toString().trim();
                Email = email.getText().toString().trim();
                Address = address.getText().toString().trim();
                Phone = phone.getText().toString().trim();

                if (male.isChecked()) {
                    Gender = "Male";
                } else if (female.isChecked()) {
                    Gender = "Female";
                } else if (others.isChecked()) {
                    Gender = "Others";
                }
                if (TextUtils.isEmpty(Username)) {
                    uName.setError("Enter Username");
                    return;
                } else if (TextUtils.isEmpty(Password)) {
                    pass.setError("Enter Password");
                    return;
                } else if (TextUtils.isEmpty(Cpassword)) {
                    cPass.setError("Confirm Your Password");
                    return;
                } else if (TextUtils.isEmpty(Email)) {
                    email.setError("Enter Email Address");
                    return;
                } else if (TextUtils.isEmpty(Phone)) {
                    phone.setError("Enter Phone Number");
                    return;
                }
                else if (TextUtils.isEmpty(Address)) {
                    address.setError("Your Location");
                    return;
                } else {
                    if (pass.getText().toString().equals(cPass.getText().toString())) {
                        if (usernameValidation()) {
                            dataTransfer();
                        }
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        pass.requestFocus();
                        return;
                    }
                }

            }
        });

    }

    //Validation
    private boolean usernameValidation() {
        boolean status = true;
        if (uName.getText().toString().length() < 6) {
            uName.setError("Minimum 6 Character / Maximum 20 Characters");
            status = false;
        }
        return status;
    }


    private void dataTransfer() {
        Intent intent = new Intent(RegistrationActivity.this, ImageActivity.class);
        intent.putExtra("Username", Username);
        intent.putExtra("Password", Password);
        intent.putExtra("Email", Email);
        intent.putExtra("Address", Address);
        intent.putExtra("Phone", Phone);
        intent.putExtra("Gender", Gender);
        startActivity(intent);
    }
}