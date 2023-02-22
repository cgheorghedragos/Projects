package com.example.greenlight.presentation.views.application.map.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlight.R;
import com.example.greenlight.data.models.MarkerModel;

import java.util.List;

public class PeopleViewAdapter extends RecyclerView.Adapter<PeopleViewAdapter.PeopleViewHolder> {
    private List<String> usernames;
    private OnClickItemListener listener;

    public PeopleViewAdapter(List<String> usernames, OnClickItemListener onClickItemListener) {
        this.usernames = usernames;
        this.listener = onClickItemListener;
    }

    public void updateList(List<String> strings){
        usernames = strings;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_added_item, parent,
                false);

        return new PeopleViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
            String currUsername = usernames.get(position);

            holder.username.setText(currUsername);

    }

    @Override
    public int getItemCount() {
        return usernames.size();
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder {
        private final TextView username;
        private final ImageView delete;

        public PeopleViewHolder(@NonNull View itemView, OnClickItemListener listener) {
            super(itemView);

            username = itemView.findViewById(R.id.username_item_people);
            delete = itemView.findViewById(R.id.delete_icon);

            delete.setOnClickListener(v -> {
                if ( listener != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onClick(pos);
                    }
                }
            });
        }
    }

}
