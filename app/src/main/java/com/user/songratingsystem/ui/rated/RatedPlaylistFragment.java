package com.user.songratingsystem.ui.rated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.user.songratingsystem.R;
import com.user.songratingsystem.adapter.UserSongsAdapter;
import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.model.Songs;
import com.user.songratingsystem.model.UserSongs;
import com.user.songratingsystem.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatedPlaylistFragment extends Fragment {

    RecyclerView ratedMusic;

    public RatedPlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rated_playlist, container, false);

        ratedMusic = view.findViewById(R.id.ratedMusic);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<UserSongs>> userCall = usersAPI.userSongList(Url.token);

        userCall.enqueue(new Callback<List<UserSongs>>() {
            @Override
            public void onResponse(Call<List<UserSongs>> call, Response<List<UserSongs>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), ""+response.code(), Toast.LENGTH_SHORT).show();
                }

                List<UserSongs> userSongs = response.body();
                UserSongsAdapter userSongsAdapter = new UserSongsAdapter(getActivity(), userSongs);
                ratedMusic.setAdapter(userSongsAdapter);
                ratedMusic.setLayoutManager(new LinearLayoutManager(getActivity()));

            }

            @Override
            public void onFailure(Call<List<UserSongs>> call, Throwable t) {

            }
        });


        return view;

    }

}
