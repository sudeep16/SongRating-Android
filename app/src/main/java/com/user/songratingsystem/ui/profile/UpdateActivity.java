package com.user.songratingsystem.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.user.songratingsystem.R;
import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.model.RegisteredUsers;
import com.user.songratingsystem.model.UpdateUsers;
import com.user.songratingsystem.strictmode.StrictModeClass;
import com.user.songratingsystem.url.Url;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    CircleImageView updImage;
    EditText updUsername, updEmail, updPhone, updAddress;
    Button updBtn, cancelBtn;
    String imgPath = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updImage = findViewById(R.id.updImage);
        updUsername= findViewById(R.id.updUsername);
        updEmail= findViewById(R.id.updEmail);
        updAddress = findViewById(R.id.updAddress);
        updPhone = findViewById(R.id.updPhone);
        updBtn = findViewById(R.id.updateBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        retrieve();

        updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, ProfileFragment.class);
                startActivity(intent);
            }
        });


    }

    public void retrieve() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<RegisteredUsers> usersCall = usersAPI.getUserDetails(Url.token);
//        Toast.makeText(UpdateActivity.this, Url.token, Toast.LENGTH_SHORT).show();
        usersCall.enqueue(new Callback<RegisteredUsers>() {
            @Override
            public void onResponse(Call<RegisteredUsers> call, Response<RegisteredUsers> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                StrictModeClass.StrictMode();
                try {

                    updUsername.setText(response.body().getUsername());
                    updEmail.setText(response.body().getEmail());
                    updPhone.setText(response.body().getPhone());
                    updAddress.setText(response.body().getAddress());

                    imgPath = Url.imagePath + response.body().getImage();
                    Picasso.get().load(imgPath).into(updImage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RegisteredUsers> call, Throwable t) {

            }
        });
    }

    public void updateProfile() {
        String etEmail = updEmail.getText().toString().trim();
        String etPhone = updPhone.getText().toString().trim();
        String etAddress = updAddress.getText().toString().trim();

        UpdateUsers updProfile = new UpdateUsers(etEmail, etPhone, etAddress);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<String> stringCall = usersAPI.updProfile(Url.token, updProfile);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "User Updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
