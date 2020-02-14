package com.user.songratingsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.songratingsystem.R;
import com.user.songratingsystem.activities.SongDetailActivity;
import com.user.songratingsystem.model.Songs;
import com.user.songratingsystem.strictmode.StrictModeClass;
import com.user.songratingsystem.url.Url;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongsViewHolder> {

    Context context;
    List<Songs> songsList;

    public SongAdapter(Context context, List<Songs> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songview, parent, false);
        return new SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
        final Songs songs = songsList.get(position);

        String imgPath = Url.imagePath + songs.getImage();
        StrictModeClass.StrictMode();
        try{
            URL url = new URL(imgPath);
            holder.songImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.songName.setText(songs.getSongTitle());
        holder.songArtist.setText(songs.getArtist());

        holder.songImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SongDetailActivity.class);
                intent.putExtra("songImage", songs.getImage());
                intent.putExtra("songTitle", songs.getSongTitle());
                intent.putExtra("songArtist", songs.getArtist());
                intent.putExtra("songGenre", songs.getGenre());
                intent.putExtra("songDuration", songs.getDuration());
                intent.putExtra("songRating", songs.getRating());
//                intent.putExtra("songFile", songs.getSongFile());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class SongsViewHolder extends RecyclerView.ViewHolder{
        ImageView songImage;
        TextView songName, songArtist;


        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            songImage = itemView.findViewById(R.id.songImage);
            songName = itemView.findViewById(R.id.songName);
            songArtist= itemView.findViewById(R.id.songArtist);

        }
    }
}
