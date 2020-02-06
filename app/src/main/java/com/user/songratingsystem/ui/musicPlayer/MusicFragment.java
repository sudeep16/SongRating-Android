package com.user.songratingsystem.ui.musicPlayer;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.user.songratingsystem.R;

import java.io.File;
import java.util.ArrayList;

public class MusicFragment extends Fragment {
    MediaPlayer player;
    ArrayList<File> mySongs;
    public MusicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_music, container, false);

        Intent i = getActivity().getIntent();
        Bundle b = i.getExtras();
        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        int position = b.getInt("pos", 0);

        Uri u = Uri.parse(mySongs.get(position).toString());
        player = MediaPlayer.create(getActivity().getApplicationContext(),u);
        player.start();




        return view;
    }

}
