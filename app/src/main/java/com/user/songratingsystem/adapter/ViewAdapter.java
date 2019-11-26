package com.user.songratingsystem.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.songratingsystem.R;
import com.user.songratingsystem.model.ViewData;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewDataViewHolder>{

    Context context;
    List<ViewData> viewDataList;

    public ViewAdapter(Context context, List<ViewData> viewDataList) {
        this.context = context;
        this.viewDataList = viewDataList;
    }

    @NonNull
    @Override
    public ViewDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songlist, parent,false);
        return new ViewDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDataViewHolder holder, int position) {
        final ViewData viewData = viewDataList.get(position);
        holder.imgProfile.setImageResource(viewData.getImageId());
        holder.viewName.setText(viewData.getName());
    }

    @Override
    public int getItemCount() {
        return viewDataList.size();
    }


    public class ViewDataViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProfile;
        TextView viewName;

        public ViewDataViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            viewName = itemView.findViewById(R.id.viewName);
        }
    }
}

