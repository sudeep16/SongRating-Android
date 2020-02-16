package com.example.songratingwearable;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songratingwearable.adapter.SongAdapter;
import com.example.songratingwearable.api.UsersAPI;
import com.example.songratingwearable.model.Songs;
import com.example.songratingwearable.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends WearableActivity {

    private RecyclerView aSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activitiy);

        aSongs = findViewById(R.id.aSong);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        //For Recommended Songs
        Call<List<Songs>> allSongs = usersAPI.getSongs(Url.token);
        allSongs.enqueue(new Callback<List<Songs>>() {
            @Override
            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Songs> allSongList = response.body();
                SongAdapter songAdapter = new SongAdapter(DashboardActivity.this, allSongList);
                aSongs.setAdapter(songAdapter);
                aSongs.setLayoutManager(new LinearLayoutManager(DashboardActivity.this, RecyclerView.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<Songs>> call, Throwable t) {

            }
        });
        // Enables Always-on
        setAmbientEnabled();
    }
}
