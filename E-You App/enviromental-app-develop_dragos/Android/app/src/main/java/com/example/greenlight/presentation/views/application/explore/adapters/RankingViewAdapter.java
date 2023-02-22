package com.example.greenlight.presentation.views.application.explore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenlight.R;
import com.example.greenlight.data.requests.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RankingViewAdapter extends RecyclerView.Adapter<RankingViewAdapter.RankingViewHolder> {
    private List<UserModel> list;

    public RankingViewAdapter() {
        list = new ArrayList<>();
    }

    public void updateList(List<UserModel> arrayList) {
        list = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_person_item, parent,
                false);

        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        UserModel model = list.get(position);
        int currPosition = position+4;

        holder.points.setText("" + model.getScore());
        holder.position.setText(""+currPosition);
        holder.username.setText("\u0040"+model.getUsername());
        if(model.getPhoto_path() != null && !model.getPhoto_path().equals("default")){
            Glide.with(holder.itemView).load(model.getPhoto_path()).into(holder.profileImage);
        }else {
            holder.profileImage.setImageResource(R.drawable.ic_decline);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RankingViewHolder extends RecyclerView.ViewHolder {
        private final ImageView profileImage;
        private final TextView position;
        private final TextView username;
        private final TextView points;

        public RankingViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImageInRankingItem);
            position = itemView.findViewById(R.id.positionInRank);
            username = itemView.findViewById(R.id.usernameItemName);
            points = itemView.findViewById(R.id.pointsItem);

        }
    }
}
