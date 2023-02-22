package com.example.greenlight.presentation.views.application.map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlight.R;
import com.example.greenlight.data.models.IncidentsModel;
import com.example.greenlight.data.models.MarkerModel;
import com.example.greenlight.presentation.viewmodel.MapViewModel;
import com.example.greenlight.presentation.views.application.map.adapters.AddIncidentPhotosAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class InfoMarkerFragment extends Fragment {
    @Inject
    public MapViewModel mapViewModel;

    private TextView closeFragment;
    private TextView incidentTitle;
    private TextView incidentDescription;
    private RecyclerView recyclerView;
    private AddIncidentPhotosAdapter recycleViewAdapter;
    private Button solveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info_marker, container, false);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.report_event_green));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        setupListeners(view);
    }

    private void setupListeners(@NonNull View view) {
        closeFragment.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_infoMarkerFragment_to_googleMapFragment2);
        });

        solveButton.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.action_infoMarkerFragment_to_solveIncidentFragment);
        });
    }

    private void setupViews(@NonNull View view) {
        closeFragment = view.findViewById(R.id.closeInfoIncident);
        incidentTitle = view.findViewById(R.id.incidentTitle);
        incidentDescription = view.findViewById(R.id.descriptionFromIncident);
        recyclerView = view.findViewById(R.id.recycleViewFromIncidents);
        solveButton = view.findViewById(R.id.goToSolveIncident);


        IncidentsModel model = mapViewModel.getSelectedIncident();

        incidentTitle.setText(model.getIncident().getIncident_title());
        incidentDescription.setText(model.getIncident().getDescription());

        LinearLayoutManager layoutManager;
        if(view.getContext() != null){
            layoutManager= new LinearLayoutManager(view.getContext());
        }else{
            layoutManager = new LinearLayoutManager(requireContext());
        }
        recyclerView.setLayoutManager(layoutManager);

        if(model.getIncident().getPhotoPath() != null){
            recycleViewAdapter = new AddIncidentPhotosAdapter(model.getIncident().getPhotoPath());
        } else{
            recycleViewAdapter = new AddIncidentPhotosAdapter(new ArrayList<>());
        }


        recyclerView.setAdapter(recycleViewAdapter);
    }
}