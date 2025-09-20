package com.example.studentapp.Fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.Pref.Pref;
import com.example.studentapp.R;


public class Add_Student_Fragment extends Fragment {

    private EditText etName, etRegNumber, etPhoneNumber, etEmail, etAadharNumber, etAddress, etDOB, etEducation, et10Marks, et10Percentage, et12Marks, et12Percentage, etSem1, etSem2, etSem3, etSem4, etSem5, etSem6, etSemOverAll;
    private DBHelper dbHelper;
    private Button professionalBtn, personalBtn, btn_next,Previous_btn,btn_save;
    private LinearLayout layout1, layout2;
    private ImageView back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add__student_, container, false);

        // Initialize Views
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
        btn_save = view.findViewById(R.id.btn_save);
        professionalBtn = view.findViewById(R.id.Professional_btn);
        personalBtn = view.findViewById(R.id.Personal_btn);
        Previous_btn = view.findViewById(R.id.Previous_btn);
        layout1 = view.findViewById(R.id.Layout_1);
        layout2 = view.findViewById(R.id.Layout_2);
        back = view.findViewById(R.id.back);
        btn_next = view.findViewById(R.id.btn_next);

        dbHelper = new DBHelper(getContext());

        toggleSection(true);

        btn_save.setOnClickListener(v -> {

            saveStudentDetails ();

            if (validateFields()) {
                saveStudentDetails();
            }
        });

        back.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        // Section Toggle Listeners
        professionalBtn.setOnClickListener(v -> toggleSection(true));
        personalBtn.setOnClickListener(v -> toggleSection(false));

        Previous_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                int primaryColor = getResources().getColor(R.color.colorPrimary);
                int grayColor = getResources().getColor(R.color.Gray_Lite);

                professionalBtn.setBackgroundTintList(ColorStateList.valueOf(primaryColor));
                personalBtn.setBackgroundTintList(ColorStateList.valueOf(grayColor));
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        } );

        btn_next.setOnClickListener ( new View.OnClickListener ( ) {
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

        return view;
    }

    private void toggleSection(boolean showProfessional) {
        int primaryColor = getResources().getColor(R.color.colorPrimary);
        int grayColor = getResources().getColor(R.color.Gray_Lite);

        professionalBtn.setBackgroundTintList(ColorStateList.valueOf(showProfessional ? primaryColor : grayColor));
        personalBtn.setBackgroundTintList(ColorStateList.valueOf(showProfessional ? grayColor : primaryColor));
        layout1.setVisibility(showProfessional ? View.VISIBLE : View.GONE);
        layout2.setVisibility(showProfessional ? View.GONE : View.VISIBLE);
        if (showProfessional) {
            etSem1.setText("");
            etSem2.setText("");
            etSem3.setText("");
            etSem4.setText("");
            etSem5.setText("");
            etSem6.setText("");
            etSemOverAll.setText("");
        } else {
            etName.setText("");
            etRegNumber.setText("");
            etPhoneNumber.setText("");
            etEmail.setText("");
            etAadharNumber.setText("");
            etAddress.setText("");
            etDOB.setText("");
            etEducation.setText("");
            et10Marks.setText("");
            et10Percentage.setText("");
            et12Marks.setText("");
            et12Percentage.setText("");
        }

    }

    private boolean validateFields() {
        if (etName.getText().toString().trim().isEmpty()) {
            etName.setError("Name is required");
            return false;
        }
        if (etRegNumber.getText().toString().trim().isEmpty()) {
            etRegNumber.setError("Registration number is required");
            return false;
        }
        if (etPhoneNumber.getText().toString().trim().isEmpty() || !etPhoneNumber.getText().toString().matches("\\d{10}")) {
            etPhoneNumber.setError("Enter a valid 10-digit phone number");
            return false;
        }
        if (etEmail.getText().toString().trim().isEmpty() || !etEmail.getText().toString().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            etEmail.setError("Enter a valid email");
            return false;
        }
        if (etAadharNumber.getText().toString().trim().isEmpty() || !etAadharNumber.getText().toString().matches("\\d{12}")) {
            etAadharNumber.setError("Enter a valid 12-digit Aadhar number");
            return false;
        }
        if (etAddress.getText().toString().trim().isEmpty()) {
            etAddress.setError("Address is required");
            return false;
        }
        if (etDOB.getText().toString().trim().isEmpty()) {
            etDOB.setError("Date of Birth is required");
            return false;
        }
        if (etEducation.getText().toString().trim().isEmpty()) {
            etEducation.setError("Education is required");
            return false;
        }
        if (et10Marks.getText().toString().trim().isEmpty()) {
            et10Marks.setError("10th Marks is required");
            return false;
        }
        if (et10Percentage.getText().toString().trim().isEmpty()) {
            et10Percentage.setError("10th Percentage is required");
            return false;
        }
        if (et12Marks.getText().toString().trim().isEmpty()) {
            et12Marks.setError("12th Marks is required");
            return false;
        }
        if (et12Percentage.getText().toString().trim().isEmpty()) {
            et12Percentage.setError("12th Percentage is required");
            return false;
        }
        if (etSem1.getText().toString().trim().isEmpty()) {
            etSem1.setError("1st Semester is required");
            return false;
        }
        if (etSem2.getText().toString().trim().isEmpty()) {
            etSem2.setError("2nd Semester is required");
            return false;
        }
        if (etSem3.getText().toString().trim().isEmpty()) {
            etSem3.setError("3rd Semester is required");
            return false;
        }
        if (etSem4.getText().toString().trim().isEmpty()) {
            etSem4.setError("4th Semester is required");
            return false;
        }
        if (etSem5.getText().toString().trim().isEmpty()) {
            etSem5.setError("5th Semester is required");
            return false;
        }
        if (etSem6.getText().toString().trim().isEmpty()) {
            etSem6.setError("6th Semester is required");
            return false;
        }
        if (etSemOverAll.getText().toString().trim().isEmpty()) {
            etSemOverAll.setError("Overall Semester is required");
            return false;
        }
        return true;
    }

    private void saveStudentDetails() {
        String vStudent_Name = etName.getText ( ).toString().trim();
        String vStudent_Reg_Number = etRegNumber.getText().toString().trim();
        String vStudent_Number = etPhoneNumber.getText().toString().trim();
        String vStudent_Email = etEmail.getText().toString().trim();
        String vStudent_Aadhar = etAadharNumber.getText().toString().trim();
        String vStudent_Address = etAddress.getText().toString().trim();
        String vStudent_DOB = etDOB.getText().toString().trim();
        String vStudent_Education = etEducation.getText().toString().trim();
        String vStudent_10_Marks = et10Marks.getText().toString().trim();
        String vStudent_10_Percentage = et10Percentage.getText().toString().trim();
        String vStudent_12_Marks = et12Marks.getText().toString().trim();
        String vStudent_12_Percentage = et12Percentage.getText().toString().trim();
        String vStudent_Sem_1 = etSem1.getText().toString().trim();
        String vStudent_Sem_2 = etSem2.getText().toString().trim();
        String vStudent_Sem_3 = etSem3.getText().toString().trim();
        String vStudent_Sem_4 = etSem4.getText().toString().trim();
        String vStudent_Sem_5 = etSem5.getText().toString().trim();
        String vStudent_Sem_6 = etSem6.getText().toString().trim();
        String vStudent_Sem_OverAll = etSemOverAll.getText().toString().trim();

        String vUser_Id = Pref.get_iUser_id(getContext());

        boolean isInserted = dbHelper.InsertStudent(
                vStudent_Name, vStudent_Reg_Number, vStudent_Number,
                vStudent_Email, vStudent_Aadhar, vStudent_Address,
                vStudent_DOB, vStudent_Education, vStudent_10_Marks,
                vStudent_10_Percentage, vStudent_12_Marks, vStudent_12_Percentage,
                vStudent_Sem_1, vStudent_Sem_2, vStudent_Sem_3,
                vStudent_Sem_4, vStudent_Sem_5, vStudent_Sem_6,
                vStudent_Sem_OverAll, vUser_Id);

        Log.e("TAG", "saveStudentDetails: isInserted : " + isInserted);

        if (isInserted) {
            Toast.makeText(getContext(), "Student details saved successfully", Toast.LENGTH_SHORT).show();
            clearFields();
            navigateToFragment(new Student_List_Fragment());
        } else {
            Toast.makeText(getContext(), "Failed to save student details", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etName.setText("");
        etRegNumber.setText("");
        etPhoneNumber.setText("");
        etEmail.setText("");
        etAadharNumber.setText("");
        etAddress.setText("");
        etDOB.setText("");
        etEducation.setText("");
        et10Marks.setText("");
        et10Percentage.setText("");
        et12Marks.setText("");
        et12Percentage.setText("");
        etSem1.setText("");
        etSem2.setText("");
        etSem3.setText("");
        etSem4.setText("");
        etSem5.setText("");
        etSem6.setText("");
        etSemOverAll.setText("");
    }


    private void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
