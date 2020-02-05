package com.user.songratingsystem.bll;

import android.util.Log;

import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.model.RegisteredUsers;
import com.user.songratingsystem.responses.LoginResponse;
import com.user.songratingsystem.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {

    boolean isSuccess = false;

    public boolean checkUser(String Username, String Password) {
        UsersAPI usersAPI = URL.getInstance().create(UsersAPI.class);
        Call<LoginResponse> usersCall = usersAPI.checkUser(Username, Password);
        try {
            Response<LoginResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login successful")) {
                URL.token += loginResponse.body().getUsertoken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}

