package com.example.greenlight.presentation.views.application.map;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlight.R;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.presentation.viewmodel.MapViewModel;
import com.example.greenlight.presentation.views.application.map.adapters.OnClickItemListener;
import com.example.greenlight.presentation.views.application.map.adapters.PeopleViewAdapter;
import com.example.greenlight.presentation.views.dialogs.LoadingDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class SolveIncidentFragment extends Fragment {

    @Inject
    public MapViewModel mapViewModel;
    private TextView closeButton;
    private TextInputLayout addPersonLayout;
    private TextInputEditText addPersonEditText;
    private RecyclerView recyclerView;
    private PeopleViewAdapter recycleViewAdapter;
    private TextView addUserIcon;
    private Button solveButton;
    private LoadingDialog loadingDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solve_incident, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupListener(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.report_event_green));
    }

    private void setupListener(@NonNull View view) {
        addPersonEditText.addTextChangedListener(userTextWatcher);
        closeButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_solveIncidentFragment_to_googleMapFragment2);
        });

        addUserIcon.setOnClickListener(v -> {
            mapViewModel.addCollaborationUser();
            addPersonEditText.setText("");
        });

        mapViewModel.getAddedCollaborationUsers().observe(requireActivity(), strings -> {
            recycleViewAdapter.updateList(strings);
        });

        solveButton.setOnClickListener(v -> {
            mapViewModel.requestSolveIncident(mapViewModel.getAuthToken());
            Navigation.findNavController(view).navigate(R.id.action_solveIncidentFragment_to_googleMapFragment2);
        });


    }

    private void setupView(@NonNull View view) {
        closeButton = view.findViewById(R.id.closeSolveIncident);
        addPersonEditText = view.findViewById(R.id.editTextAddPerson);
        addPersonLayout = view.findViewById(R.id.textInputLayoutAddingPeople);
        recyclerView = view.findViewById(R.id.userAddedRecycleView);
        addUserIcon = view.findViewById(R.id.addUserTextView);
        recycleViewAdapter = new PeopleViewAdapter(new ArrayList<>(), onClickItemListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recycleViewAdapter);
        solveButton = view.findViewById(R.id.solveBtn);
        loadingDialog = new LoadingDialog(view.getContext());
    }

    private OnClickItemListener onClickItemListener = new OnClickItemListener() {
        @Override
        public void onClick(int position) {
            mapViewModel.removeCollaborationUser(position);
        }
    };

    private TextWatcher userTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mapViewModel.updateCurrentCollaborationUser(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}