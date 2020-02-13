package com.user.songratingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.songratingsystem.R;
import com.user.songratingsystem.model.Songs;
import com.user.songratingsystem.strictmode.StrictModeClass;
import com.user.songratingsystem.url.Url;

import java.io.InputStream;
import java.net.URL;

public class SongDetailActivity extends AppCompatActivity {
    ImageView song_image;
    TextView song_title, song_artist, song_genre, song_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        song_image = findViewById(R.id.song_image);
        song_title = findViewById(R.id.song_title);
        song_artist = findViewById(R.id.song_artist);
        song_genre = findViewById(R.id.song_genre);
        song_duration = findViewById(R.id.song_duration);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String photo = bundle.getString("songImage");
            Songs songs = new Songs("", "", "", "", photo);

            String imgPath = Url.imagePath + songs.getImage();
            StrictModeClass.StrictMode();
            try {
                URL url = new URL(imgPath);
                song_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            song_title.setText(bundle.getString("songTitle"));
            song_artist.setText(bundle.getString("songArtist"));
            song_genre.setText(bundle.getString("songGenre"));
            song_duration.setText(bundle.getString("songDuration"));
        }
    }
}
