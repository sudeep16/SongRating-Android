package com.user.songratingsystem.adapter;

import android.app.Notification;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.user.songratingsystem.R;
import com.user.songratingsystem.api.UsersAPI;
import com.user.songratingsystem.createChannel.CreateChannel;
import com.user.songratingsystem.model.Songs;
import com.user.songratingsystem.model.UserSongs;
import com.user.songratingsystem.strictmode.StrictModeClass;
import com.user.songratingsystem.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSongsAdapter extends RecyclerView.Adapter<UserSongsAdapter.uSongViewHolder> {

    Context context;
    List<UserSongs> uSongsList;
    int Counter;
    private NotificationManagerCompat notificationManagerCompat;

    public UserSongsAdapter(Context context, List<UserSongs> uSongsList) {
        this.context = context;
        this.uSongsList = uSongsList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<UserSongs> getuSongsList() {
        return uSongsList;
    }

    public void setuSongsList(List<UserSongs> uSongsList) {
        this.uSongsList = uSongsList;
    }

    @NonNull
    @Override
    public uSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usersonglist, parent, false);
        return new uSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull uSongViewHolder holder, final int position) {
        final UserSongs userSongs = uSongsList.get(position);

        holder.uSongTitle.setText(userSongs.getSongTitle());
        holder.uSongArtist.setText(userSongs.getArtist());
        holder.uSongGenre.setText(userSongs.getGenre());
        holder.uSongDuration.setText(userSongs.getDuration());
        holder.uSongRating.setText(userSongs.getRating());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notificationManagerCompat = NotificationManagerCompat.from(context);
                CreateChannel notify = new CreateChannel(context);
                notify.createChannel();

                Notification notification = new NotificationCompat.Builder(context, CreateChannel.Notify_1)
                        .setSmallIcon(R.drawable.ic_star_border_black_24dp)
                        .setContentTitle("Deleted")
                        .setContentText("You have Deleted " + userSongs.getSongTitle())
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();
                notificationManagerCompat.notify(1,notification);

                Counter++;

                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<List<UserSongs>> delCall = usersAPI.userSongDelete(Url.token);

                delCall.enqueue(new Callback<List<UserSongs>>() {
                    @Override
                    public void onResponse(Call<List<UserSongs>> call, Response<List<UserSongs>> response) {
                        if(response.isSuccessful()){
                            uSongsList.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Song Has Been Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "" + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserSongs>> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return uSongsList.size();
    }

    public class uSongViewHolder extends RecyclerView.ViewHolder {
        TextView uSongTitle, uSongArtist, uSongGenre, uSongDuration, uSongRating;
        Button deleteBtn;

        public uSongViewHolder(@NonNull View itemView) {
            super(itemView);

            uSongTitle = itemView.findViewById(R.id.uSongTitle);
            uSongArtist = itemView.findViewById(R.id.uSongArtist);
            uSongGenre = itemView.findViewById(R.id.uSongGenre);
            uSongDuration = itemView.findViewById(R.id.uSongDuration);
            uSongRating = itemView.findViewById(R.id.uSongRate);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }

    public void rateNotify(){

    }
}
