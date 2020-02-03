package com.user.songratingsystem.api;

import android.telecom.Call;

import com.user.songratingsystem.responses.ImageResponse;
import com.user.songratingsystem.responses.RegisterResponse;

public interface ratingAPI {
    //Create user
    @POST("users/signup")
    Call<RegisterResponse> registerUser(@Body CreateUser usr);

    //login user
    @POST("users/signin")
    Call<LoginResponses>login(@Body CreateUser user);

    @Multipart
    @POST("users/profile")
    Call<ImageResponse>uploadImage(@Part MultipartBody.Part image);

    @GET("users/detail")
    Call<CreateUser> getUserDetails(@Header("Authorization")String token);

    @PUT("users/update/password")
    Call<LoginResponses> updatePassword(@Header("Authorization") String token, @Body CreateUser user);

}
