package com.user.songratingsystem.ui.profile;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.user.songratingsystem.R;
import com.user.songratingsystem.activities.LoginActivity;
import com.user.songratingsystem.activities.Register.ImageActivity;
import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.model.RegisteredUsers;
import com.user.songratingsystem.strictmode.StrictModeClass;
import com.user.songratingsystem.url.Url;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    ImageView imgProfile;
    TextView dispUsername, dispEmail, dispPhone, dispAddress, dispGender;
    Button editBtn, logoutBtn;
    String imgPath = " ";
    UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgProfile = view.findViewById(R.id.imgProfile);
        dispUsername = view.findViewById(R.id.dispUsername);
        dispEmail = view.findViewById(R.id.dispEmail);
        dispPhone = view.findViewById(R.id.dispPhone);
        dispAddress = view.findViewById(R.id.dispAddress);
        dispGender = view.findViewById(R.id.dispGender);
        editBtn = view.findViewById(R.id.editBtn);
        logoutBtn = view.findViewById(R.id.logoutBtn);

        userRetrieve();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url.token = null;
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), Url.token + "You have been Logged Out", Toast.LENGTH_SHORT).show();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void userRetrieve() {
        Call<RegisteredUsers> usersCall = usersAPI.getUserDetails(Url.token);
        usersCall.enqueue(new Callback<RegisteredUsers>() {
            @Override
            public void onResponse(Call<RegisteredUsers> call, Response<RegisteredUsers> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                StrictModeClass.StrictMode();
                try {

                    dispUsername.setText(response.body().getUsername());
                    dispEmail.setText(response.body().getEmail());
                    dispPhone.setText(response.body().getPhone());
                    dispAddress.setText(response.body().getAddress());
                    dispGender.setText(response.body().getGender());

                    imgPath = Url.imagePath + response.body().getImage();
                    Picasso.get().load(imgPath).into(imgProfile);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RegisteredUsers> call, Throwable t) {

            }
        });
    }
}

//try {
//        Response<RegisteredUsers> usersResponse = usersCall.execute();
//        if (usersResponse.isSuccessful() &&
//        !usersResponse.body().getPhone().isEmpty()) {
//        Picasso.get().load(Url.base_url + usersResponse.body().getImage()).resize(400, 110).centerCrop().into(imgProfile);
//        dispUsername.setText(usersResponse.body().getUsername());
//        dispEmail.setText(usersResponse.body().getEmail());
//        dispPhone.setText(usersResponse.body().getPhone());
//        dispAddress.setText(usersResponse.body().getAddress());
//        dispGender.setText(usersResponse.body().getGender());
//        }
//        } catch (IOException e) {
//        e.printStackTrace();
//        }
//        }

