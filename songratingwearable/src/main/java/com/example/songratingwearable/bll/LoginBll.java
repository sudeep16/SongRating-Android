package com.example.songratingwearable.bll;

import com.example.songratingwearable.api.UsersAPI;
import com.example.songratingwearable.responses.RegisterResponse;
import com.example.songratingwearable.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<RegisterResponse> usersCall = usersAPI.checkUser(username, password);

        try {
            Response<RegisterResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login Successful")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
