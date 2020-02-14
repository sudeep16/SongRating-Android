package com.user.songratingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.user.songratingsystem.R;
import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.createChannel.CreateChannel;
import com.user.songratingsystem.model.Songs;
import com.user.songratingsystem.model.UserSongs;
import com.user.songratingsystem.strictmode.StrictModeClass;
import com.user.songratingsystem.ui.profile.ProfileFragment;
import com.user.songratingsystem.ui.profile.UpdateActivity;
import com.user.songratingsystem.url.Url;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongDetailActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    ImageView song_image;
    TextView song_title, song_artist, song_genre, song_duration;
    Button sAddBtn, sCancelBtn;
    RatingBar ratingBar;
    TextView rate;
    float ratedStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        song_image = findViewById(R.id.song_image);
        song_title = findViewById(R.id.song_title);
        song_artist = findViewById(R.id.song_artist);
        song_genre = findViewById(R.id.song_genre);
        song_duration = findViewById(R.id.song_duration);
        sAddBtn = findViewById(R.id.sAddBtn);
        sCancelBtn = findViewById(R.id.sCancelBtn);
        ratingBar = findViewById(R.id.rateBar);
        rate = findViewById(R.id.rating);

        //Displaying Rating Bar Data on Change
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate.setText("Your Rating : " + rating);
                ratedStar = rating;

            }
        });

        //Calling Notification Manager
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel notify = new CreateChannel(this);
        notify.createChannel();


        //Button Click Events
        sCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongDetailActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        sAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToPlaylist();

            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String photo = bundle.getString("songImage");
            Songs songs = new Songs("", "", "", "", photo, "");

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
            rate.setText(bundle.getString("SongRating"));
        }
    }

    public void addToPlaylist(){
        String songTitle = song_title.getText().toString().trim();
        String songArtist = song_artist.getText().toString().trim();
        String songGenre = song_genre.getText().toString().trim();
        String songDuration = song_duration.getText().toString().trim();
        String rated = Float.toString(ratedStar);

        UserSongs uSongs = new UserSongs(songTitle, songArtist, songGenre, songDuration, rated);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Void> voidCall = usersAPI.userSongs(Url.token, uSongs);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(SongDetailActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(SongDetailActivity.this, "Added to the Playlist", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void rateNotify(){
//        Notification notification = new NotificationCompat.Builder(this, CreateChannel.Notify_1)
//                .setSmallIcon()

    }
}
