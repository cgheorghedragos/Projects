package com.example.greenlight.presentation.views.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.greenlight.R;
import com.example.greenlight.presentation.views.application.explore.ExploreFragment;
import com.example.greenlight.presentation.views.application.explore.RankingFragment;
import com.example.greenlight.presentation.views.application.map.MapContainerFragment;
import com.example.greenlight.presentation.views.application.profile.ProfileFragment;
import com.google.android.material.tabs.TabLayout;

import dagger.android.AndroidInjection;

public class ApplicationActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        setupViews();
        setupListeners();
    }

    private void setupListeners() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    private void setupViews() {
        tabLayout = this.findViewById(R.id.tab_Layout);
        viewPager = this.findViewById(R.id.viewPagerPanel);
        viewPager.setUserInputEnabled(false);
        tabAdapter = new TabAdapter(getSupportFragmentManager(),getLifecycle());

        tabAdapter.addFragments(new RankingFragment());
        tabAdapter.addFragments(new MapContainerFragment());
        tabAdapter.addFragments(new ProfileFragment());

        viewPager.setAdapter(tabAdapter);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}