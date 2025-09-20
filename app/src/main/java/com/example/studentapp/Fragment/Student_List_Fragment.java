package com.example.studentapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.studentapp.Activity.HomeActivity;
import com.example.studentapp.Adapter.Student_List_Adapter;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.Module.Student_list_Items;
import com.example.studentapp.Pref.Pref;
import com.example.studentapp.R;

import java.util.ArrayList;

public class Student_List_Fragment extends Fragment {

    private static final String TAG = "Student_List_Fragment";

    private RecyclerView recyclerView;
    private ImageView back, refresh;

    private DBHelper dbHelper;
    private Student_List_Adapter studentListAdapter;
   private ArrayList<Student_list_Items> studentListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student__list_, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        back = view.findViewById(R.id.back);


        dbHelper = new DBHelper(getContext());

        String iUser_Id = Pref.get_iUser_id(getContext ());

        Log.e ( "TAG" , "run: iUser_Id1 : "+iUser_Id );

        studentListItems = new ArrayList<> ();

        studentListItems = dbHelper.Read_Student_List(Integer.parseInt (iUser_Id));
        Log.e(TAG, "onCreateView: studentListItems size: " + studentListItems.size());
        studentListAdapter = new Student_List_Adapter (getActivity (), Student_List_Fragment.this);
        studentListAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        studentListAdapter.addPosts(studentListItems);
        recyclerView.setAdapter(studentListAdapter);


        back.setOnClickListener(v -> navigateToFragment(new DashboardFragment ()));

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        refreshStudentList();
    }
    private void refreshStudentList() {

        studentListAdapter.clear();
        studentListItems.clear();
        String iUser_Id = Pref.get_iUser_id(getContext ());
        Log.e ( "TAG" , "run: iUser_Id1 : "+iUser_Id );

        studentListItems = dbHelper.Read_Student_List(Integer.parseInt (iUser_Id));
        Log.e(TAG, "onCreateView: studentListItems size: " + studentListItems.size());
        studentListAdapter = new Student_List_Adapter (getActivity (), Student_List_Fragment.this);
        studentListAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        studentListAdapter.addPosts(studentListItems);
        recyclerView.setAdapter(studentListAdapter);
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
