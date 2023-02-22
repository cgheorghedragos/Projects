package com.example.greenlight.presentation.views.application.explore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.greenlight.R;
import com.example.greenlight.data.requests.UserModel;
import com.example.greenlight.presentation.viewmodel.RankingViewModel;
import com.example.greenlight.presentation.views.application.explore.adapters.RankingViewAdapter;
import com.example.greenlight.presentation.views.application.map.adapters.ReportEventRecycleViewAdapter;
import com.example.greenlight.presentation.views.dialogs.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RankingFragment extends Fragment {
    @Inject
    public RankingViewModel rankingViewModel;
    private final int NUMBER_OF_PEOPLE = 1000;
    private RecyclerView recyclerView;
    private RankingViewAdapter rankingViewAdapter;
    private TextView firstWinnerName;
    private TextView secondWinnerName;
    private TextView thirdWinnerName;
    private TextView firstWinnerScore;
    private TextView secondWinnerScore;
    private TextView thirdWinnerScore;
    private ImageView firstWinnerImageView;
    private LoadingDialog loadingDialog;
    private ImageView secondWinnerImageView;
    private ImageView thirdWinnerImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.ranking_light_black_green));
        setupViews(view);
        setupListeners();
        rankingViewModel.requestUsersRanking(NUMBER_OF_PEOPLE);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        rankingViewModel.requestUsersRanking(NUMBER_OF_PEOPLE);
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.ranking_light_black_green));
    }

    private void setupListeners() {
        rankingViewModel.getUsersByRank().observe(requireActivity(), users -> {
            switch (users.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    loadingDialog.showDialog();
                    break;
                case SUCCESS:
                    loadingDialog.hideDialog();
                    updateTop3Users(users.getData().getData(),getContext());
                    updateRecycleView(users.getData().getData());
                    break;
                case ERROR:
                    loadingDialog.hideDialog();
            }
        });
    }

    private void updateRecycleView(ArrayList<UserModel> data) {
        List<UserModel> people = new ArrayList<>(data);
        people.remove(0);
        people.remove(0);
        people.remove(0);

        rankingViewAdapter.updateList(people);
    }

    private void updateTop3Users(ArrayList<UserModel> data,Context context) {
        try{
            firstWinnerScore.setText(""+data.get(0).getScore());
            firstWinnerName.setText("\u0040"+data.get(0).getUsername());
            if(data.get(0).getPhoto_path() != null && !data.get(0).getPhoto_path().equals("default")){
                Glide.with(context).load(data.get(0).getPhoto_path()).into(firstWinnerImageView);
            }else {
                firstWinnerImageView.setImageResource(R.drawable.ic_decline);
            }

            secondWinnerScore.setText(""+data.get(1).getScore());
            secondWinnerName.setText("\u0040"+data.get(1).getUsername());
            if(data.get(1).getPhoto_path() != null && !data.get(1).getPhoto_path().equals("default")){
                Glide.with(context).load(data.get(1).getPhoto_path()).into(secondWinnerImageView);
            }else {
                secondWinnerImageView.setImageResource(R.drawable.ic_decline);
            }

            thirdWinnerScore.setText(""+data.get(2).getScore());
            thirdWinnerName.setText("\u0040"+data.get(2).getUsername());
            if(data.get(2).getPhoto_path() != null && !data.get(2).getPhoto_path().equals("default")){
                Glide.with(context).load(data.get(2).getPhoto_path()).into(thirdWinnerImageView);
            }else {
                thirdWinnerImageView.setImageResource(R.drawable.ic_decline);
            }
        } catch (Exception exception){
            Log.e("LOAD_PROF_PIC",exception.getMessage());
        }

    }

    private void setupViews(@NonNull View view) {
        recyclerView = view.findViewById(R.id.rankingRecycleView);
        firstWinnerName = view.findViewById(R.id.firstWinnerName);
        firstWinnerScore= view.findViewById(R.id.firstWinnerPoints);
        firstWinnerImageView = view.findViewById(R.id.firstWinnerImageView);
        secondWinnerName = view.findViewById(R.id.secondWinnerName);
        secondWinnerScore= view.findViewById(R.id.secondWinnerPoints);
        secondWinnerImageView = view.findViewById(R.id.secondWinnerImageView);
        thirdWinnerName = view.findViewById(R.id.thirdWinnerName);
        thirdWinnerScore= view.findViewById(R.id.thirdWinnerPoints);
        thirdWinnerImageView = view.findViewById(R.id.thirdWinnerImageView);
        loadingDialog = new LoadingDialog(view.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        rankingViewAdapter = new RankingViewAdapter();

        recyclerView.setAdapter(rankingViewAdapter);
    }
}