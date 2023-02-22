package com.example.greenlight.presentation.views.application.map;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenlight.R;
import com.example.greenlight.data.models.MarkerModel;
import com.example.greenlight.presentation.views.application.map.adapters.OnClickItemListener;
import com.example.greenlight.presentation.views.application.map.adapters.ReportEventRecycleViewAdapter;
import com.example.greenlight.presentation.viewmodel.MapViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class ReportActionFragment extends Fragment {
    @Inject
    public MapViewModel mapViewModel;

    private RecyclerView recyclerView;
    private ReportEventRecycleViewAdapter recycleViewAdapter;
    private TextView closeReportFragment;
    private final int NUMBER_OF_COLUMN = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_action, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.report_event_green));
        setupViews(view);
        setupListeners();

    }
    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.report_event_green));
    }

    private void setupListeners() {
        closeReportFragment.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_reportActionFragment_to_googleMapFragment2);
        });


    }

    private void setupViews(@NonNull View view) {

        closeReportFragment = view.findViewById(R.id.closeMapActionButton);

        recyclerView = view.findViewById(R.id.recycleViewMarkers);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), NUMBER_OF_COLUMN);

        recyclerView.setLayoutManager(layoutManager);

        recycleViewAdapter = new ReportEventRecycleViewAdapter(mapViewModel.getEventIcons().getValue(),onClickItemListener);

        recyclerView.setAdapter(recycleViewAdapter);
    }



    public OnClickItemListener onClickItemListener = new OnClickItemListener() {
        @Override
        public void onClick(int position) {

            MarkerModel marker = mapViewModel.getEventIcons().getValue().get(position);
            mapViewModel.updateMarker(new MarkerModel(marker.getMarkerType(),marker.getMarkerDescription()));
            Navigation.findNavController(requireView()).navigate(R.id.action_reportActionFragment_to_reportDetailFragment);
        }
    };
}