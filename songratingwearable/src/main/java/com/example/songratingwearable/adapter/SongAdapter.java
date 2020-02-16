package com.example.songratingwearable.adapter;

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

import com.example.songratingwearable.DashboardActivity;
import com.example.songratingwearable.R;
import com.example.songratingwearable.model.Songs;
import com.example.songratingwearable.strictmode.StrictModeClass;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songlist, parent, false);
        return new SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
        final Songs songs = songsList.get(position);
        StrictModeClass.StrictMode();

        holder.songName.setText(songs.getSongTitle());
        holder.songGenre.setText(songs.getGenre());

        holder.songImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DashboardActivity.class);
                intent.putExtra("songTitle", songs.getSongTitle());
                intent.putExtra("songGenre", songs.getGenre());

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
        TextView songName, songGenre;


        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.songName);
            songGenre= itemView.findViewById(R.id.songGenre);

        }
    }
}
