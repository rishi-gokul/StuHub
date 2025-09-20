package com.example.studentapp.Fragment;

import static android.content.ContentValues.TAG;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.Activity.HomeActivity;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.R;

public class ViewFragment extends Fragment {
    private TextView etName, etRegNumber, etPhoneNumber, etEmail, etAadharNumber, etAddress, etDOB, etEducation, et10Marks, et10Percentage, et12Marks, et12Percentage, etSem1, etSem2, etSem3, etSem4, etSem5, etSem6, etSemOverAll;
    private DBHelper dbHelper;
    private Button professionalBtn, personalBtn, btnNext, btnPrevious;
    private LinearLayout layout1, layout2;
    private ImageView back;

    String vStudent_Name,VStudent_Reg_Number, vStudent_Number, vStudent_Email, vStudent_Aadher, vStudent_DOB, vStudent_Address, vStudent_Education, vStudent_10_Marks, vStudent_10_Percentage, vStudent_12_Marks, vStudent_12_Percentage, vStudent_Sem_1, vStudent_Sem_2, vStudent_Sem_3, vStudent_Sem_4, vStudent_Sem_5, vStudent_Sem_6, vStudent_Sem_OverAll;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        // Initialize views and set up listeners
        Intent_data_Passing();
        initViews(view);
        setupListeners();

        // Initialize database helper
        dbHelper = new DBHelper(requireContext());

        // Load student details from the database
        loadStudentDetails();

        return view;
    }
    public void Intent_data_Passing() {
        Bundle bundle = this.getArguments();
        String sub = null;
        if (bundle != null) {
            vStudent_Name = bundle.getString("vStudent_Name", sub);
            VStudent_Reg_Number = bundle.getString("vStudent_RegNumber", sub);
            vStudent_Number = bundle.getString("vStudent_Number", sub);
            vStudent_Email = bundle.getString("vStudent_Email", sub);
            vStudent_Aadher = bundle.getString("vStudent_Aadher", sub);
            vStudent_DOB = bundle.getString("vStudent_DOB", sub);
            vStudent_Address = bundle.getString("vStudent_Address", sub);
            vStudent_Education = bundle.getString("vStudent_Education", sub);
            vStudent_10_Marks = bundle.getString("vStudent_10_Marks", sub);
            vStudent_10_Percentage = bundle.getString("vStudent_10_Percentage", sub);
            vStudent_12_Marks = bundle.getString("vStudent_12_Marks", sub);
            vStudent_12_Percentage = bundle.getString("vStudent_12_Percentage", sub);
            vStudent_Sem_1 = bundle.getString("vStudent_Sem_1", sub);
            vStudent_Sem_2 = bundle.getString("vStudent_Sem_2", sub);
            vStudent_Sem_3 = bundle.getString("vStudent_Sem_3", sub);
            vStudent_Sem_4 = bundle.getString("vStudent_Sem_4", sub);
            vStudent_Sem_5 = bundle.getString("vStudent_Sem_5", sub);
            vStudent_Sem_6 = bundle.getString("vStudent_Sem_6", sub);
            vStudent_Sem_OverAll = bundle.getString("vStudent_Sem_OverAll", sub);

        }

        Log.e(TAG, "Intent_data_Passing:vStudent_Name : "+vStudent_Name+" vStudent_Number : "+vStudent_Number+" vStudent_Email : "+vStudent_Email+ " vStudent_Aadher : "+vStudent_Aadher+" vStudent_DOB : "+vStudent_DOB+" vStudent_Address : "+vStudent_Address+" vStudent_Education : "+vStudent_Education+" vStudent_10_Marks : "+vStudent_10_Marks+" vStudent_10_Percentage : "+vStudent_10_Percentage+" vStudent_12_Marks : "+vStudent_12_Marks+" vStudent_12_Percentage : "+vStudent_12_Percentage+" vStudent_Sem_1 : "+vStudent_Sem_1+" vStudent_Sem_2 : "+vStudent_Sem_2+" vStudent_Sem_3 : "+vStudent_Sem_3+" vStudent_Sem_4 : "+vStudent_Sem_4+" vStudent_Sem_5 : "+vStudent_Sem_5+" vStudent_Sem_6 : "+vStudent_Sem_6+" vStudent_Sem_OverAll : "+vStudent_Sem_OverAll);

    }

    private void initViews(View view) {

        back = view.findViewById(R.id.back);
        etName = view.findViewById(R.id.et_name);
        etRegNumber = view.findViewById(R.id.et_regnumber);
        etPhoneNumber = view.findViewById(R.id.et_phonenumber);
        etEmail = view.findViewById(R.id.et_email);
        etAadharNumber = view.findViewById(R.id.et_aadharnumber);
        etAddress = view.findViewById(R.id.et_address);
        etDOB = view.findViewById(R.id.et_Dob);
        etEducation = view.findViewById(R.id.et_Education);
        et10Marks = view.findViewById(R.id.et_10th_mark);
        et10Percentage = view.findViewById(R.id.et_10th_per);
        et12Marks = view.findViewById(R.id.et_12th_mark);
        et12Percentage = view.findViewById(R.id.et_12th_Per);
        etSem1 = view.findViewById(R.id.et_1st_sem);
        etSem2 = view.findViewById(R.id.et_2nd_sem);
        etSem3 = view.findViewById(R.id.et_3rd_sem);
        etSem4 = view.findViewById(R.id.et_4th_sem);
        etSem5 = view.findViewById(R.id.et_5th_sem);
        etSem6 = view.findViewById(R.id.et_6th_sem);
        etSemOverAll = view.findViewById(R.id.et_over_sem);
        professionalBtn = view.findViewById(R.id.Professional_btn);
        personalBtn = view.findViewById(R.id.Personal_btn);
        btnNext = view.findViewById(R.id.btn_next);
        btnPrevious = view.findViewById(R.id.Previous_btn);
        layout1 = view.findViewById(R.id.Layout_1);
        layout2 = view.findViewById(R.id.Layout_2);
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
    }

    private void setupListeners() {

        back.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        btnPrevious.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        professionalBtn.setOnClickListener(v -> toggleLayouts(false));
        personalBtn.setOnClickListener(v -> toggleLayouts(true));
        int primaryColor = getResources().getColor(R.color.colorPrimary);
        int grayColor = getResources().getColor(R.color.Gray_Lite);
        professionalBtn.setBackgroundTintList(ColorStateList.valueOf(grayColor));
        personalBtn.setBackgroundTintList(ColorStateList.valueOf(primaryColor));
        layout2.setVisibility(View.GONE);
        layout1.setVisibility(View.VISIBLE);

        btnNext.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                int primaryColor = getResources().getColor(R.color.colorPrimary);
                int grayColor = getResources().getColor(R.color.Gray_Lite);

                professionalBtn.setBackgroundTintList(ColorStateList.valueOf(grayColor));
                personalBtn.setBackgroundTintList(ColorStateList.valueOf(primaryColor));
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        } );

    }
    private void toggleLayouts(boolean showPersonal) {
        layout2.setVisibility(showPersonal ? View.VISIBLE : View.GONE);
        layout1.setVisibility(showPersonal ? View.GONE : View.VISIBLE);
        int primaryColor = getResources().getColor(R.color.colorPrimary);
        int grayColor = getResources().getColor(R.color.Gray_Lite);
        professionalBtn.setBackgroundTintList(showPersonal ? ColorStateList.valueOf(grayColor) : ColorStateList.valueOf(primaryColor));
        personalBtn.setBackgroundTintList(showPersonal ? ColorStateList.valueOf(primaryColor) : ColorStateList.valueOf(grayColor));
        Intent_data_Passing();
    }
    private void loadStudentDetails() {
                etName.setText(vStudent_Name);
                etRegNumber.setText(VStudent_Reg_Number);
                etPhoneNumber.setText(vStudent_Number);
                etEmail.setText(vStudent_Email);
                etAadharNumber.setText(vStudent_Aadher);
                etAddress.setText(vStudent_Address);
                etDOB.setText(vStudent_DOB);
                etEducation.setText(vStudent_Education);
                et10Marks.setText(vStudent_10_Marks);
                et10Percentage.setText(vStudent_10_Percentage);
                et12Marks.setText(vStudent_12_Marks);
                et12Percentage.setText(vStudent_12_Percentage);
                etSem1.setText(vStudent_Sem_1);
                etSem2.setText(vStudent_Sem_2);
                etSem3.setText(vStudent_Sem_3);
                etSem4.setText(vStudent_Sem_4);
                etSem5.setText(vStudent_Sem_5);
                etSem6.setText(vStudent_Sem_6);
                etSemOverAll.setText(vStudent_Sem_OverAll);

    }
}

