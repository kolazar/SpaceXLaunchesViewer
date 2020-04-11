package com.example.randomgallery.spacexdataviewer.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomgallery.spacexdataviewer.R;
import com.example.randomgallery.spacexdataviewer.model.Launch;

import java.util.Collections;
import java.util.List;

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.LaunchViewHolder> {

    private List<Launch> launches = Collections.emptyList();

    public void setLaunches(List<Launch> launches){
        this.launches = launches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LaunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_repository,parent,false);
        return new LaunchViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchViewHolder holder, int position) {
        Launch launch = launches.get(position);
        holder.nameTextView.setText(launch.getMissionName());
    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    static class LaunchViewHolder extends RecyclerView.ViewHolder{

       private TextView nameTextView;

        public LaunchViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
