package com.example.greenlight.presentation.views.application.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.greenlight.MainActivity;
import com.example.greenlight.R;
import com.example.greenlight.data.requests.UserModel;
import com.example.greenlight.presentation.viewmodel.ProfileViewModel;
import com.example.greenlight.presentation.views.application.ApplicationActivity;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class ProfileFragment extends Fragment {
    @Inject
    public ProfileViewModel profileViewModel;

    private ImageView profileImage;
    private TextView username;
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView birthday;
    private Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        setupListeners(view);

        profileViewModel.requestMyAccount();
    }

    private void setupViews(@NonNull View view) {
        profileImage = view.findViewById(R.id.userProfilePhoto);
        username = view.findViewById(R.id.usernameInUserProfile);
        firstName = view.findViewById(R.id.textViewFirstNameUserProf);
        lastName = view.findViewById(R.id.textViewLastNameUserProf);
        email = view.findViewById(R.id.textViewEmailUserProf);
        birthday = view.findViewById(R.id.textViewBirthdayUserProf);
        logout = view.findViewById(R.id.logoutButton);
    }

    private void setupListeners(@NonNull View view) {
        logout.setOnClickListener( v -> {
            startActivity(new Intent(requireActivity(), MainActivity.class));
            requireActivity().finish();
        });

        profileViewModel.getMyAccount().observe(requireActivity(), userModel -> {
          try{
              username.setText(""+"\u0040"+userModel.getUsername());
              firstName.setText(userModel.getFirst_name());
              lastName.setText(userModel.getLast_name());
              email.setText(userModel.getEmail());
              birthday.setText(userModel.getBirthday());

              if(userModel.getPhoto_path() != null && !userModel.getPhoto_path().equals("default")){
                  Glide.with(view.getContext()).load(userModel.getPhoto_path()).into(profileImage);
              }else {
                  profileImage.setImageResource(R.drawable.ic_decline);
              }
          } catch (NullPointerException nullPointerException){
              Log.e("GET_ACCOUNT",nullPointerException.getMessage());
          }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.report_event_green));
    }

}