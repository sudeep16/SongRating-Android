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
import android.widget.Button;
import android.widget.SeekBar;

import com.user.songratingsystem.R;

import java.io.File;
import java.util.ArrayList;

public class MusicFragment extends Fragment implements View.OnClickListener {
    MediaPlayer player;
    ArrayList<File> mySongs;
    int position;
    Uri u;
    SeekBar seek;
    Button play, seekBack, seekFront, next, previous;

    public MusicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_music, container, false);

        play = view.findViewById(R.id.play);
        seekBack = view.findViewById(R.id.seekBack);
        seekFront = view.findViewById(R.id.seekFront);
        next = view.findViewById(R.id.next);
        previous = view.findViewById(R.id.previous);

        play.setOnClickListener(this);
        seekBack.setOnClickListener(this);
        seekFront.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);


        Intent i = getActivity().getIntent();
        Bundle b = i.getExtras();
        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        position = b.getInt("pos", 0);

        u = Uri.parse(mySongs.get(position).toString());
        player = MediaPlayer.create(getActivity().getApplicationContext(),u);
        player.start();




        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.play:
                if(player.isPlaying()){
                    play.setText(">");
                    player.pause();
                } else
                    play.setText("||");
                    player.start();

                break;
            case R.id.seekFront:
                player.seekTo(player.getCurrentPosition()+5000);
                break;
            case R.id.seekBack:
                player.seekTo(player.getCurrentPosition()-5000);
                break;
            case R.id.next:
                player.stop();
                player.release();
                u = Uri.parse(mySongs.get((position+1)%mySongs.size()).toString());
                player = MediaPlayer.create(getActivity().getApplicationContext(),u);
                player.start();
                break;
            case R.id.previous:
                player.stop();
                player.release();
                position = (position-1<0)? mySongs.size()-1: position-1;
//                if(position-1 <0){
//                    position = mySongs.size()-1;
//                }
//                else {
//                    position = position-1
//                }
                u = Uri.parse(mySongs.get(position).toString());
                player = MediaPlayer.create(getActivity().getApplicationContext(),u);
                player.start();
                break;
        }
    }
}
