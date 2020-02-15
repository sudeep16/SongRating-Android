package com.user.songratingsystem.ui.home;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.songratingsystem.R;
import com.user.songratingsystem.activities.DashboardActivity;
import com.user.songratingsystem.adapter.SongAdapter;
import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.model.Songs;
import com.user.songratingsystem.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView allSongs, hardRock, classicRock, romance;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        allSongs = view.findViewById(R.id.allSongs);
        hardRock = view.findViewById(R.id.hSong);
        classicRock = view.findViewById(R.id.cSong);
        romance = view.findViewById(R.id.Romance);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        //For Recommended Songs
        Call<List<Songs>> recommSongs = usersAPI.getSongs(Url.token);
        recommSongs.enqueue(new Callback<List<Songs>>() {
            @Override
            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Songs> recommSongList = response.body();
                SongAdapter songAdapter = new SongAdapter(getActivity(), recommSongList);
                allSongs.setAdapter(songAdapter);
                allSongs.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<Songs>> call, Throwable t) {

            }
        });

        //For Romantic Songs
        Call<List<Songs>> rSongs = usersAPI.getRomanceGenre(Url.token);
        rSongs.enqueue(new Callback<List<Songs>>() {
            @Override
            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Songs> rSongList = response.body();
                SongAdapter songAdapter = new SongAdapter(getActivity(), rSongList);
                romance.setAdapter(songAdapter);
                romance.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<Songs>> call, Throwable t) {

            }
        });

        //For Hard Rock Songs
        Call<List<Songs>> hSong = usersAPI.getHardRockGenre(Url.token);
        hSong.enqueue(new Callback<List<Songs>>() {
            @Override
            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Songs> hRockList = response.body();
                SongAdapter songAdapter = new SongAdapter(getActivity(), hRockList);
                hardRock.setAdapter(songAdapter);
                hardRock.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<Songs>> call, Throwable t) {

            }
        });

        //For Classic Rock Songs
        Call<List<Songs>> cSong = usersAPI.getClassicRockGenre(Url.token);
        cSong.enqueue(new Callback<List<Songs>>() {
            @Override
            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Songs> cRockList = response.body();
                SongAdapter songAdapter = new SongAdapter(getActivity(), cRockList);
                classicRock.setAdapter(songAdapter);
                classicRock.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<Songs>> call, Throwable t) {

            }
        });
        return view;

    }

}