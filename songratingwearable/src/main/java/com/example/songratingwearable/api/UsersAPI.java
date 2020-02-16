package com.example.songratingwearable.api;

import com.example.songratingwearable.model.Songs;
import com.example.songratingwearable.responses.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UsersAPI {
    @FormUrlEncoded
    @POST("users/login")
    Call<RegisterResponse> checkUser(@Field("Username") String username, @Field("Password") String password);

    @GET("song")
    Call<List<Songs>> getSongs(@Header("Authorization") String token);

}