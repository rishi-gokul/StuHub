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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.Pref.Pref;
import com.example.studentapp.R;

public class EditFragment extends Fragment {
    private TextView etName, etRegNumber, etPhoneNumber, etEmail, etAadharNumber, etAddress, etDOB, etEducation, et10Marks, et10Percentage, et12Marks, et12Percentage, etSem1, etSem2, etSem3, etSem4, etSem5, etSem6, etSemOverAll;
    private DBHelper dbHelper;
    private Button professionalBtn, personalBtn, btnNext, btnPrevious, btnSave;
    private LinearLayout layout1, layout2;
    private ImageView back;
    private String vStudent_ID,vStudent_Name, VStudent_Reg_Number, vStudent_Number, vStudent_Email, vStudent_Aadher, vStudent_DOB, vStudent_Address, vStudent_Education, vStudent_10_Marks, vStudent_10_Percentage, vStudent_12_Marks, vStudent_12_Percentage, vStudent_Sem_1, vStudent_Sem_2, vStudent_Sem_3, vStudent_Sem_4, vStudent_Sem_5, vStudent_Sem_6, vStudent_Sem_OverAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        Intent_data_Passing();
        initViews(view);
        setupListeners();
        dbHelper = new DBHelper(requireContext());
        loadStudentDetails();

        return view;
    }

    private void Intent_data_Passing() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            vStudent_ID = bundle.getString("vStudent_ID", null);
            vStudent_Name = bundle.getString("vStudent_Name", null);
            VStudent_Reg_Number = bundle.getString("vStudent_RegNumber", null);
            vStudent_Number = bundle.getString("vStudent_Number", null);
            vStudent_Email = bundle.getString("vStudent_Email", null);
            vStudent_Aadher = bundle.getString("vStudent_Aadher", null);
            vStudent_DOB = bundle.getString("vStudent_DOB", null);
            vStudent_Address = bundle.getString("vStudent_Address", null);
            vStudent_Education = bundle.getString("vStudent_Education", null);
            vStudent_10_Marks = bundle.getString("vStudent_10_Marks", null);
            vStudent_10_Percentage = bundle.getString("vStudent_10_Percentage", null);
            vStudent_12_Marks = bundle.getString("vStudent_12_Marks", null);
            vStudent_12_Percentage = bundle.getString("vStudent_12_Percentage", null);
            vStudent_Sem_1 = bundle.getString("vStudent_Sem_1", null);
            vStudent_Sem_2 = bundle.getString("vStudent_Sem_2", null);
            vStudent_Sem_3 = bundle.getString("vStudent_Sem_3", null);
            vStudent_Sem_4 = bundle.getString("vStudent_Sem_4", null);
            vStudent_Sem_5 = bundle.getString("vStudent_Sem_5", null);
            vStudent_Sem_6 = bundle.getString("vStudent_Sem_6", null);
            vStudent_Sem_OverAll = bundle.getString("vStudent_Sem_OverAll", null);
        }
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
        btnSave = view.findViewById(R.id.btn_save);
        btnNext = view.findViewById(R.id.btn_next);
        btnPrevious = view.findViewById(R.id.Previous_btn);
        layout1 = view.findViewById(R.id.Layout_1);
        layout2 = view.findViewById(R.id.Layout_2);
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);

        btnSave.setOnClickListener(v -> {
            if (validateFields()) {
                saveStudentDetails();
            }
        });
    }

    private void saveStudentDetails() {
        String vStudent_Name = etName.getText().toString().trim();
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


        boolean isUpdated = dbHelper.UpdateStudent(vStudent_ID,vStudent_Name, vStudent_Reg_Number, vStudent_Number, vStudent_Email, vStudent_Aadhar, vStudent_Address, vStudent_DOB, vStudent_Education, vStudent_10_Marks, vStudent_10_Percentage, vStudent_12_Marks, vStudent_12_Percentage, vStudent_Sem_1, vStudent_Sem_2, vStudent_Sem_3, vStudent_Sem_4, vStudent_Sem_5, vStudent_Sem_6, vStudent_Sem_OverAll, vUser_Id);
        if (isUpdated) {
            Toast.makeText(getContext(), "Student details updated successfully", Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack();
        }
        else {
            Toast.makeText(getContext(), "Failed to update student details", Toast.LENGTH_SHORT).show();
        }
        Log.e("TAG", "saveStudentDetails: " + vStudent_Name);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Reg_Number);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Number);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Email);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Aadhar);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Address);
        Log.e("TAG", "saveStudentDetails: " + vStudent_DOB);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Education);
        Log.e("TAG", "saveStudentDetails: " + vStudent_10_Marks);
        Log.e("TAG", "saveStudentDetails: " + vStudent_10_Percentage);
        Log.e("TAG", "saveStudentDetails: " + vStudent_12_Marks);
        Log.e("TAG", "saveStudentDetails: " + vStudent_12_Percentage);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Sem_1);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Sem_2);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Sem_3);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Sem_4);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Sem_5);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Sem_6);
        Log.e("TAG", "saveStudentDetails: " + vStudent_Sem_OverAll);
        Log.e("TAG", "saveStudentDetails: " + vUser_Id);
    }

    private boolean validateFields() {

        return true;
    }

    private void setupListeners() {
        back.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        btnPrevious.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        professionalBtn.setOnClickListener(v -> toggleLayouts(false));
        personalBtn.setOnClickListener(v -> toggleLayouts(true));
        btnNext.setOnClickListener(v -> toggleLayouts(true));
    }

    private void toggleLayouts(boolean showPersonal) {
        layout2.setVisibility(showPersonal ? View.VISIBLE : View.GONE);
        layout1.setVisibility(showPersonal ? View.GONE : View.VISIBLE);
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
