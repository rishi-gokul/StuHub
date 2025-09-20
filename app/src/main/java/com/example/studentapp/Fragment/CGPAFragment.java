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

import com.example.studentapp.Module.GradeModel;
import com.example.studentapp.R;

import java.util.ArrayList;

public class CGPAFragment extends Fragment {
    private ImageView back;
    private LinearLayout subjectsContainer;
    private Button addSubjectButton, calculateButton;
    private TextView resultText;
    private ArrayList<GradeModel> subjectList;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_c_g_p_a, container, false);

        subjectsContainer = view.findViewById(R.id.subjectsContainer);
        addSubjectButton = view.findViewById(R.id.addSubjectButton);
        calculateButton = view.findViewById(R.id.calculateButton);
        resultText = view.findViewById(R.id.resultText);
        back = view.findViewById(R.id.back);

        subjectList = new ArrayList<>();

        addSubjectButton.setOnClickListener(v -> addSubjectField());
        back.setOnClickListener(v -> navigateToFragment(new DashboardFragment ()));
        calculateButton.setOnClickListener(v -> calculateCGPA());

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

    private void addSubjectField() {
        View subjectView = getLayoutInflater().inflate(R.layout.fragment_result, null);
        subjectsContainer.addView(subjectView);
    }

    private void calculateCGPA() {
        int childCount = subjectsContainer.getChildCount();
        double totalGradePoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < childCount; i++) {
            View subjectView = subjectsContainer.getChildAt(i);
            EditText gradeInput = subjectView.findViewById(R.id.gradeInput);
            EditText creditInput = subjectView.findViewById(R.id.creditInput);

            String gradeStr = gradeInput.getText().toString();
            String creditStr = creditInput.getText().toString();

            if (!gradeStr.isEmpty() && !creditStr.isEmpty()) {
                double grade = Double.parseDouble(gradeStr);
                int credit = Integer.parseInt(creditStr);
                totalGradePoints += grade * credit;
                totalCredits += credit;
            }
        }

        if (totalCredits > 0) {
            double cgpa = totalGradePoints / totalCredits;
            resultText.setText("CGPA: " + String.format("%.2f", cgpa));
        } else {
            resultText.setText("Enter valid grades and credits!");
        }
    }
}
