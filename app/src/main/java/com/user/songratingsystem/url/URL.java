package com.user.songratingsystem.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {
//         public static final String base_url = "http://192.168.1.9:2020/";
        public static final String base_url = "http://10.0.2.2:2020/";
        // public static final String base_url = "http://172.100.100.5:2020/";

        public static String token = "Bearer ";
        public static String imagePath = base_url + "uploads/" ;

        public static Retrofit getInstance() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
    }

