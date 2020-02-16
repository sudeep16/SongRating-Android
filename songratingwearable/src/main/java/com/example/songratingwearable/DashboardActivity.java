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

    private TextView aSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activitiy);

        aSongs = findViewById(R.id.aSong);

        aSongs.setText("Welcome");

        // Enables Always-on
        setAmbientEnabled();
    }
}
