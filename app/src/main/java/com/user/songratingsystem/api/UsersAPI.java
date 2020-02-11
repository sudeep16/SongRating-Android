package com.user.songratingsystem.api;

import com.user.songratingsystem.model.RegisteredUsers;
import com.user.songratingsystem.responses.ImageResponse;
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
    @POST("users/registration")
    Call<RegisterResponse> registerUser(@Body RegisteredUsers users);

    @FormUrlEncoded
    @POST("users/login")
    Call<RegisterResponse> checkUser(@Field("Username") String username, @Field("Password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/profile")
    Call<RegisteredUsers> getUserDetails(@Header("Authorization")String token);

}