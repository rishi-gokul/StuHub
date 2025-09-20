
package com.example.studentapp.Fragment;

import static com.example.studentapp.R.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.studentapp.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ResultFragment extends Fragment {
    private LinearLayout subjectsContainer;
    private Button addSubjectButton, calculateButton;
    private TextView resultText;
    private ArrayList<View> subjectViews;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_result, container, false);

        subjectsContainer = view.findViewById(R.id.subjectsContainer);
        addSubjectButton = view.findViewById(R.id.addSubjectButton);
        calculateButton = view.findViewById(R.id.calculateButton);
        resultText = view.findViewById(R.id.resultText);
        subjectViews = new ArrayList<>();

        addSubjectButton.setOnClickListener(v -> addSubjectField());
        calculateButton.setOnClickListener(v -> calculateCGPA());

        return view;
    }

    private void addSubjectField() {
        Context context = getContext();
        if (context == null) return;

        // Create MaterialCardView
        MaterialCardView cardView = new MaterialCardView(context);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, dpToPx(8)); // Margin bottom 8dp
        cardView.setLayoutParams(cardParams);
        cardView.setCardElevation(dpToPx(4));
        cardView.setRadius(dpToPx(12));
        cardView.setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));
        cardView.setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.white));

        // Create horizontal LinearLayout for inputs
        LinearLayout inputLayout = new LinearLayout(context);
        inputLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        inputLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Create Grade Input Layout
        TextInputLayout gradeInputLayout = new TextInputLayout(context);
        gradeInputLayout.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1
        ));
        gradeInputLayout.setHint("Grade (e.g. 9.0)");

        TextInputEditText gradeInput = new TextInputEditText(context);
        gradeInput.setId(View.generateViewId());
        gradeInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        gradeInputLayout.addView(gradeInput);

        // Create Credit Input Layout
        TextInputLayout creditInputLayout = new TextInputLayout(context);
        LinearLayout.LayoutParams creditParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1
        );
        creditParams.setMargins(dpToPx(8), 0, 0, 0); // Add space between fields
        creditInputLayout.setLayoutParams(creditParams);
        creditInputLayout.setHint("Credits");

        TextInputEditText creditInput = new TextInputEditText(context);
        creditInput.setId(View.generateViewId());
        creditInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        creditInputLayout.addView(creditInput);

        // Add fields to layout
        inputLayout.addView(gradeInputLayout);
        inputLayout.addView(creditInputLayout);
        cardView.addView(inputLayout);

        // Add card to container
        subjectsContainer.addView(cardView);
        subjectViews.add(cardView);
    }

    private void calculateCGPA() {
        double totalGradePoints = 0;
        int totalCredits = 0;

        for (View subjectView : subjectViews) {
            TextInputEditText gradeInput = subjectView.findViewById(View.generateViewId());
            TextInputEditText creditInput = subjectView.findViewById(View.generateViewId());

            if (gradeInput != null && creditInput != null) {
                String gradeStr = gradeInput.getText().toString();
                String creditStr = creditInput.getText().toString();

                if (!gradeStr.isEmpty() && !creditStr.isEmpty()) {
                    double grade = Double.parseDouble(gradeStr);
                    int credit = Integer.parseInt(creditStr);
                    totalGradePoints += grade * credit;
                    totalCredits += credit;
                }
            }
        }

        if (totalCredits > 0) {
            double cgpa = totalGradePoints / totalCredits;
            resultText.setText("CGPA: " + String.format("%.2f", cgpa));
        } else {
            resultText.setText("Enter valid grades and credits!");
        }
    }

    // Utility method to convert dp to pixels
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
