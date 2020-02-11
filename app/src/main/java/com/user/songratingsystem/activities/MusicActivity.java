package com.user.songratingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.user.songratingsystem.R;

import java.io.File;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    static MediaPlayer player;
    ArrayList<File> mySongs;
    int position;
    Uri u;
    SeekBar seek;
    Button play, seekBack, seekFront, next, previous;
    Thread updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        play = findViewById(R.id.play);
        seekBack = findViewById(R.id.seekBack);
        seekFront = findViewById(R.id.seekFront);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);

        play.setOnClickListener(this);
        seekBack.setOnClickListener(this);
        seekFront.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);

        seek = (SeekBar) findViewById(R.id.seekBar);
        updateSeekBar = new Thread(){
            @Override
            public void run() {
                int totalDuration = player.getDuration();
                int currentPosition = 0;
                while (currentPosition < totalDuration){
                    try{
                        sleep(500);
                        currentPosition = player.getCurrentPosition();
                        seek.setProgress(currentPosition);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                //super.run();
            }
        };

        if(player!=null){
            player.stop();
            player.release();
        }

        Intent i = getIntent();
        Bundle b = i.getExtras();
        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        position = b.getInt("pos", 0);

        u = Uri.parse(mySongs.get(position).toString());
        player = MediaPlayer.create(getApplicationContext(),u);
        player.start();
        seek.setMax(player.getDuration());

        updateSeekBar.start();

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });

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
                player = MediaPlayer.create(getApplicationContext(),u);
                player.start();
                seek.setMax(player.getDuration());
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
                player = MediaPlayer.create(getApplicationContext(),u);
                player.start();
                seek.setMax(player.getDuration());
                break;
        }
    }
}



