package com.user.songratingsystem.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.user.songratingsystem.R;
import com.user.songratingsystem.activities.MusicActivity;

import java.io.File;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView listView;
    String[] items;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) view.findViewById(R.id.lvSongs);

        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());
        items = new String[mySongs.size() ];
        for(int i = 0; i<mySongs.size(); i++){
            //toast(mySongs.get(i).getName().toString());
            items[i] = mySongs.get(i).getName().toString().replace(".mp3","");
        }

        ArrayAdapter<String> adapterSong = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.songlist, R.id.songName,items);
        listView.setAdapter(adapterSong);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity().getApplicationContext(), MusicActivity.class).putExtra("pos", position).putExtra("songlist", mySongs));

            }
        });

        return view;

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

    public void toast(String text){
        Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }



}