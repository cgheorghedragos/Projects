package com.example.greenlight.presentation.views.application.map.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenlight.R;
import com.example.greenlight.utils.Converters;

import java.util.List;

public class AddIncidentPhotosAdapter extends RecyclerView.Adapter<AddIncidentPhotosAdapter.AddIncidentViewHolder> {
    private List<String> listURLForImage;

    public AddIncidentPhotosAdapter(List<String> addedImage) {
        this.listURLForImage = addedImage;
    }

    public void updateList(List<String> arrayList){
        listURLForImage = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddIncidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_added_item, parent,
                false);

        return new AddIncidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddIncidentViewHolder holder, int position) {
        String imgURL = listURLForImage.get(position);

        Glide.with(holder.addedImage).load(imgURL).into(holder.addedImage);
    }

    @Override
    public int getItemCount() {
        return listURLForImage.size();
    }

    public static class AddIncidentViewHolder extends RecyclerView.ViewHolder {
        private final ImageView addedImage;

        public AddIncidentViewHolder(@NonNull View itemView) {
            super(itemView);

            addedImage = itemView.findViewById(R.id.addedImage);
        }

    }
}
