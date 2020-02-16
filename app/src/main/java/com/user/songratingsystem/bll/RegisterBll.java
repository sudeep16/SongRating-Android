package com.user.songratingsystem.bll;

import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.model.RegisteredUsers;
import com.user.songratingsystem.responses.RegisterResponse;
import com.user.songratingsystem.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBll {
    boolean isSuccess = false;

    public boolean registerUser(String username, String password, String email, String phone, String address, String gender, String image) {

        RegisteredUsers users = new RegisteredUsers(username, password, email, phone, address, gender, image);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<RegisterResponse> usersCall = usersAPI.registerUser(users);

        try {
            Response<RegisterResponse> registerResponse = usersCall.execute();
            if (registerResponse.isSuccessful() &&
                    registerResponse.body().getStatus().equals("!! User Registration Successfull !!")) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
