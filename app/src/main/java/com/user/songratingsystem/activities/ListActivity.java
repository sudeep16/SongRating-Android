package com.user.songratingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.user.songratingsystem.R;

import java.io.File;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.lvSongs);

//        ArrayList<String> mySongs = findSongs(Environment.getExternalStorageDirectory());
//        items = new String[mySongs.size() ];
//        for(int i = 0; i<mySongs.size(); i++){
//            items[i] = mySongs.get(i).getName().toString();
//        }

        ArrayAdapter<String> adapterSong = new ArrayAdapter<String>(getApplicationContext(), R.layout.songlist, R.id.songName,items);
        listView.setAdapter(adapterSong);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                startActivity(new Intent(getApplicationContext(), MusicActivity.class).putExtra("pos", position).putExtra("songlist", mySongs));

            }
        });
    }

    public ArrayList<File> findSongs(File root){
        ArrayList<File> arraySongs = new ArrayList<File>();
        File[] files = root.listFiles();
        for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arraySongs.addAll(findSongs(singleFile));
            }
            else {
                if(singleFile.getName().endsWith(".mp3")){
                    arraySongs.add(singleFile);

                }
            }
        }
        return arraySongs;
    }

}

