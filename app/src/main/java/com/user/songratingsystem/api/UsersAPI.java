package com.user.songratingsystem.api;

import com.user.songratingsystem.model.RegisteredUsers;
import com.user.songratingsystem.responses.ImageResponse;
import com.user.songratingsystem.responses.LoginResponse;
import com.user.songratingsystem.responses.RegisterResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UsersAPI {
    @POST("users/signup")
    retrofit2.Call<RegisterResponse> registerUser(@Body RegisteredUsers users);

    @FormUrlEncoded
    @POST("users/login")
    retrofit2.Call<RegisterResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @Multipart
    @POST("upload")
    retrofit2.Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<RegisteredUsers> getUserDetails(@Header("Authorization")String token);

}