package com.example.studentapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.studentapp.R;

import java.util.ArrayList;

public class CheckFragment extends Fragment {
    private ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_check, container, false);
        back = view.findViewById(R.id.back);
        back.setOnClickListener(v -> navigateToFragment(new SettingFragment ()));
        return view;
    }
    private void navigateToFragment(Fragment fragment) {
        AppCompatActivity activity = (AppCompatActivity) requireContext();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

}