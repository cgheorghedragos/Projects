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
import com.example.greenlight.utils.Converters;

import java.util.ArrayList;
import java.util.List;

public class ReportEventRecycleViewAdapter extends RecyclerView.Adapter<ReportEventRecycleViewAdapter.ReportEventViewHolder> {
    private List<MarkerModel> markers;
    private OnClickItemListener listener;

    public ReportEventRecycleViewAdapter(List<MarkerModel> markers, OnClickItemListener onClickItemListener) {
        this.markers = markers;
        this.listener = onClickItemListener;
    }

    @NonNull
    @Override
    public ReportEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item_recycleview, parent,
                false);

        return new ReportEventViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportEventViewHolder holder, int position) {
        MarkerModel marker = markers.get(position);

        holder.typeEventName.setText(marker.getMarkerDescription());
        holder.imageEventName.setImageResource(Converters.getMarkerIcon(marker.getMarkerType()));
    }


    @Override
    public int getItemCount() {
        return markers.size();
    }

    public static class ReportEventViewHolder extends RecyclerView.ViewHolder {
        private final TextView typeEventName;
        private final ImageView imageEventName;

        public ReportEventViewHolder(@NonNull View itemView, OnClickItemListener listener) {
            super(itemView);

            typeEventName = itemView.findViewById(R.id.typeIncidentItem);
            imageEventName = itemView.findViewById(R.id.reportedActionImageView);

            itemView.setOnClickListener(v -> {
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