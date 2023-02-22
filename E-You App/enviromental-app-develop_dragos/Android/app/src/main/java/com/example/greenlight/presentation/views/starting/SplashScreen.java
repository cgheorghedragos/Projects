package com.example.greenlight.presentation.views.starting;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.greenlight.R;

import dagger.android.support.AndroidSupportInjection;


public class SplashScreen extends Fragment {
    private CardView imageAppName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }


    private void addFadeAnim(View view) {
        Animation fadeInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_anim);
        view.startAnimation(fadeInAnim);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageAppName = view.findViewById(R.id.splashScreenCardView);
        addFadeAnim(imageAppName);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_signInFragment);
            }
        }, 3000);
    }
}