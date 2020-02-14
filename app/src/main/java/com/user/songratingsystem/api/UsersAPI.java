package com.user.songratingsystem.api;

import com.user.songratingsystem.model.RegisteredUsers;
import com.user.songratingsystem.model.Songs;
import com.user.songratingsystem.model.UpdateUsers;
import com.user.songratingsystem.model.UserSongs;
import com.user.songratingsystem.responses.ImageResponse;
import com.user.songratingsystem.responses.RegisterResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @PUT("users/updProfile")
    Call<String> updProfile(@Header("Authorization")String token, @Body UpdateUsers updateUsers);

    @GET("song")
    Call<List<Songs>> getSongs(@Header("Authorization") String token);

    @GET("song/Romance")
    Call<List<Songs>> getRomanceGenre(@Header("Authorization") String token);

    @GET("song/HardRock")
    Call<List<Songs>> getHardRockGenre(@Header("Authorization") String token);

    @GET("song/ClassicRock")
    Call<List<Songs>> getClassicRockGenre(@Header("Authorization") String token);

    @POST("uSong")
    Call<Void> userSongs(@Header("Authorization") String token, @Body UserSongs uSongList);

    @GET("uSong")
    Call<List<UserSongs>> userSongList(@Header("Authorization") String token);

    @DELETE("uSong")
    Call<List<UserSongs>> userSongDelete(@Header("Authorization") String token);



}