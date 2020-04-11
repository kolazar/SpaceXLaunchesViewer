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

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.LaunchViewHolder>
                                implements View.OnClickListener{

    private List<Launch> launches = Collections.emptyList();
    private LaunchListener listener;

    public LaunchesAdapter(LaunchListener listener) {
        setHasStableIds(true);
        this.listener = listener;
    }

    public void setLaunches(List<Launch> launches){
        this.launches = launches;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Launch launch = (Launch) v.getTag();
        listener.onLaunchChosen(launch);
    }

    @Override
    public long getItemId(int position) {
        return launches.get(position).getFlightNumber();
    }

    @NonNull
    @Override
    public LaunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_repository,parent,false);
        return new LaunchViewHolder(root,this);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchViewHolder holder, int position) {
        Launch launch = launches.get(position);
        holder.nameTextView.setText(launch.getMissionName());
        holder.launchDateTextView.setText(launch.getLaunchDate());
        holder.itemView.setTag(launch);
    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    static class LaunchViewHolder extends RecyclerView.ViewHolder{

       private TextView nameTextView;
       private TextView launchDateTextView;

        public LaunchViewHolder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            launchDateTextView = itemView.findViewById(R.id.launchDateTextView);
            itemView.setOnClickListener(listener);
        }
    }

    public interface LaunchListener{
        void onLaunchChosen(Launch launch);
    }
}
