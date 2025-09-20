package com.example.studentapp.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.studentapp.R;

public class List_Fragment extends Fragment {

    private CardView imagesCard, img;
    private View view;
    private ImageView back;


    public List_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_list_ , container , false );
        initializeViews ( view );
        setClickEvents ( view );
        return view;
    }

    private void initializeViews(View view) {
        imagesCard = view.findViewById ( R.id.imageCard );
        img = view.findViewById ( R.id.img );
        back = view.findViewById ( R.id.back );

    }

    private void setClickEvents(View view) {
        this.view = view;
        imagesCard.setOnClickListener(v -> navigateToFragment (new NotesFragment ()));
        back.setOnClickListener(v -> navigateToFragment (new DashboardFragment ()));
        img.setOnClickListener(v -> navigateToFragment (new ImageFragment ()));

    }

    private void navigateToFragment(Fragment fragment) {
        AppCompatActivity activity = (AppCompatActivity) requireContext();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container , fragment)
                .addToBackStack(null)
                .commit();
    }
}



