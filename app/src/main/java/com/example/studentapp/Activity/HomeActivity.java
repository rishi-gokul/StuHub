package com.example.studentapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.studentapp.Fragment.DashboardFragment;
import com.example.studentapp.Fragment.List_Fragment;
import com.example.studentapp.Fragment.SettingFragment;
import com.example.studentapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class HomeActivity extends AppCompatActivity {

    Fragment fragment;
    private ImageView back;
    FrameLayout fragment_container;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setStatusBarColor();
        // Initialize and assign variable
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        fragment_container = findViewById(R.id.fragment_container);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        fragment = new DashboardFragment();
        loadFragment(fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// Set the icon size (in pixels)
        int iconSizeInDp = 18; // Desired icon size in dp
        int iconSizeInPx = (int) (iconSizeInDp * getResources().getDisplayMetrics().density);
        bottomNavigationView.setItemIconSize(iconSizeInPx);


        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId ( ) == R.id.dashboard) {
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    fragment_container.setVisibility(View.VISIBLE);
                    return true;
                } else if (item.getItemId ( ) == R.id.home) {
                    fragment = new List_Fragment();
                    loadFragment(fragment);
                    fragment_container.setVisibility(View.VISIBLE);
                    return true;
                } else if (item.getItemId ( ) == R.id.about) {
                    fragment = new SettingFragment();
                    loadFragment(fragment);
                    fragment_container.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }}
            );
    }

    private void setStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.white));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
