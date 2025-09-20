package com.example.studentapp.Adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.Fragment.EditFragment;
import com.example.studentapp.Fragment.Student_List_Fragment;
import com.example.studentapp.Fragment.ViewFragment;
import com.example.studentapp.Module.Student_list_Items;
import com.example.studentapp.R;

import java.util.ArrayList;
import java.util.List;


public class Student_List_Adapter extends RecyclerView.Adapter<Student_List_Adapter.ViewHolder> {

    private static Context context;
    private static List<Student_list_Items> posts = new ArrayList<> ( );
    ArrayList<String> lstChk = new ArrayList<> ( );

    private Student_List_Fragment student_list_fragment;
    @SuppressLint("RestrictedApi")
    private SafeIterableMap<Object, Object> studentList;


    public Student_List_Adapter(Context context, Student_List_Fragment student_list_fragment) {
        this.context = context;
        this.student_list_fragment = student_list_fragment;
        setHasStableIds(true);
    }



    public void addPosts(List<Student_list_Items> student_list_items) {
        this.posts.clear();
        Log.e (TAG , "addPosts: " + " 1 : " + student_list_items.size ( ));
        this.posts.addAll (student_list_items);
        notifyDataSetChanged( );

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        return new ViewHolder
                (LayoutInflater.from (context).inflate (R.layout.ly_student_list_ly ,
                        parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder , int position) {
        Log.e (TAG , "onBindViewHolder:Railcar_Checkbox :" + position + " posts.size() : " + posts.size ( ));
        int railcar_inc = position + 1;

        holder.bindPost (posts.get (position) , railcar_inc , lstChk);

    }

    @Override
    public int getItemCount() {
        return posts.size ( );
    }

    public void clear() {
        int size = posts.size ( );
        posts.clear ( );
        notifyItemRangeRemoved (0 , size);
    }


    @Override
    public int getItemViewType(int position) {
        Log.e (TAG , "getItemViewType: " + position);
        return position;
    }

    public Student_List_Fragment getStudent_list_fragment() {
        return student_list_fragment;
    }

    public void setStudent_list_fragment(Student_List_Fragment student_list_fragment) {
        this.student_list_fragment = student_list_fragment;
    }

    public void setStudentList(@SuppressLint("RestrictedApi") SafeIterableMap<Object, Object> studentList) {
        this.studentList = studentList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Student_Name;
        public ImageView Product_Delete, Name_box_ly, edit;
        LinearLayout view;


        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            this.Student_Name = (TextView) itemView.findViewById (R.id.Student_Name);
            this.Name_box_ly = (ImageView) itemView.findViewById (R.id.Name_box_ly);
            Product_Delete = itemView.findViewById(R.id.Product_Delete);
            edit = itemView.findViewById(R.id.edit);

        }

        public void bindPost(final Student_list_Items post , int railcar_inc , ArrayList lstChk) {



            String vStudent_ID = post.getvStudent_ID();
            String vStudent_Name = post.getvStudent_Name();
            String vStudent_RegNumber = post.getvStudent_RegNumber();
            String vStudent_Number = post.getvStudent_Number();
            String vStudent_Email = post.getvStudent_Email();
            String vStudent_Aadher = post.getvStudent_Aadher();
            String vStudent_DOB = post.getvStudent_DOB();
            String vStudent_Address = post.getvStudent_Address();
            String vStudent_Education = post.getvStudent_Education();
            String vStudent_10_Marks = post.getvStudent_10_Marks();
            String vStudent_10_Percentage = post.getvStudent_10_Percentage();
            String vStudent_12_Marks = post.getvStudent_12_Marks();
            String vStudent_12_Percentage = post.getvStudent_12_Percentage();
            String vStudent_Sem_1 = post.getvStudent_Sem_1();
            String vStudent_Sem_2 = post.getvStudent_Sem_2();
            String vStudent_Sem_3 = post.getvStudent_Sem_3();
            String vStudent_Sem_4 = post.getvStudent_Sem_4();
            String vStudent_Sem_5 = post.getvStudent_Sem_5();
            String vStudent_Sem_6 = post.getvStudent_Sem_6();
            String vStudent_Sem_OverAll = post.getvStudent_Sem_OverAll();

            Student_Name.setText ( vStudent_Name);
            Log.e ( TAG , "bindPost: vStudent_Name3 : "+vStudent_Name );
            Log.e ( TAG , "bindPost: vStudent_RegNumber3 : "+vStudent_RegNumber );
            Log.e ( TAG , "bindPost: vStudent_Number3 : "+vStudent_Number );
            Log.e ( TAG , "bindPost: vStudent_Email3 : "+vStudent_Email );
            Log.e ( TAG , "bindPost: vStudent_Aadher3 : "+vStudent_Aadher );
            Log.e ( TAG , "bindPost: vStudent_DOB3 : "+vStudent_DOB );
            Log.e ( TAG , "bindPost: vStudent_Address3 : "+vStudent_Address );
            Log.e ( TAG , "bindPost: vStudent_Education3 : "+vStudent_Education );
            Log.e ( TAG , "bindPost: vStudent_10_Marks3 : "+vStudent_10_Marks );
            Log.e ( TAG , "bindPost: vStudent_10_Percentage3 : "+vStudent_10_Percentage );
            Log.e ( TAG , "bindPost: vStudent_12_Marks3 : "+vStudent_12_Marks );
            Log.e ( TAG , "bindPost: vStudent_12_Percentage3 : "+vStudent_12_Percentage );
            Log.e ( TAG , "bindPost: vStudent_Sem_13 : "+vStudent_Sem_1 );
            Log.e ( TAG , "bindPost: vStudent_Sem_23 : "+vStudent_Sem_2 );
            Log.e ( TAG , "bindPost: vStudent_Sem_33 : "+vStudent_Sem_3 );
            Log.e ( TAG , "bindPost: vStudent_Sem_43 : "+vStudent_Sem_4 );
            Log.e ( TAG , "bindPost: vStudent_Sem_53 : "+vStudent_Sem_5 );
            Log.e ( TAG , "bindPost: vStudent_Sem_63 : "+vStudent_Sem_6 );
            Log.e ( TAG , "bindPost: vStudent_Sem_OverAll3 : "+vStudent_Sem_OverAll );


            Name_box_ly.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    ViewFragment orders_list_fragment = new ViewFragment();
                    Bundle bundle_cate = new Bundle();
                    bundle_cate.putString("vStudent_ID", vStudent_ID);
                    bundle_cate.putString("vStudent_Name", vStudent_Name);
                    bundle_cate.putString("vStudent_RegNumber", vStudent_RegNumber);
                    bundle_cate.putString("vStudent_Number", vStudent_Number);
                    bundle_cate.putString("vStudent_Email", vStudent_Email);
                    bundle_cate.putString("vStudent_Aadher", vStudent_Aadher);
                    bundle_cate.putString("vStudent_DOB", vStudent_DOB);
                    bundle_cate.putString("vStudent_Address", vStudent_Address);
                    bundle_cate.putString("vStudent_Education", vStudent_Education);
                    bundle_cate.putString("vStudent_10_Marks", vStudent_10_Marks);
                    bundle_cate.putString("vStudent_10_Percentage", vStudent_10_Percentage);
                    bundle_cate.putString("vStudent_12_Marks", vStudent_12_Marks);
                    bundle_cate.putString("vStudent_12_Percentage", vStudent_12_Percentage);
                    bundle_cate.putString("vStudent_Sem_1", vStudent_Sem_1);
                    bundle_cate.putString("vStudent_Sem_2", vStudent_Sem_2);
                    bundle_cate.putString("vStudent_Sem_3", vStudent_Sem_3);
                    bundle_cate.putString("vStudent_Sem_4", vStudent_Sem_4);
                    bundle_cate.putString("vStudent_Sem_5", vStudent_Sem_5);
                    bundle_cate.putString("vStudent_Sem_6", vStudent_Sem_6);
                    bundle_cate.putString("vStudent_Sem_OverAll", vStudent_Sem_OverAll);
                    Log.e ( TAG , "onClick: vStudent_Name : "+vStudent_Name );
                    Log.e ( TAG , "onClick: vStudent_Number : "+vStudent_Number );
                    Log.e ( TAG , "onClick: vStudent_Email : "+vStudent_Email );
                    Log.e ( TAG , "onClick: vStudent_Aadher : "+vStudent_Aadher );
                    Log.e ( TAG , "onClick: vStudent_DOB : "+vStudent_DOB );
                    Log.e ( TAG , "onClick: vStudent_Address : "+vStudent_Address );
                    Log.e ( TAG , "onClick: vStudent_Education : "+vStudent_Education );
                    Log.e ( TAG , "onClick: vStudent_10_Marks : "+vStudent_10_Marks );
                    Log.e ( TAG , "onClick: vStudent_10_Percentage : "+vStudent_10_Percentage );
                    Log.e ( TAG , "onClick: vStudent_12_Marks : "+vStudent_12_Marks );
                    Log.e ( TAG , "onClick: vStudent_12_Percentage : "+vStudent_12_Percentage );
                    Log.e ( TAG , "onClick: vStudent_Sem_1 : "+vStudent_Sem_1 );
                    Log.e ( TAG , "onClick: vStudent_Sem_2 : "+vStudent_Sem_2 );
                    Log.e ( TAG , "onClick: vStudent_Sem_3 : "+vStudent_Sem_3 );
                    Log.e ( TAG , "onClick: vStudent_Sem_4 : "+vStudent_Sem_4 );
                    Log.e ( TAG , "onClick: vStudent_Sem_5 : "+vStudent_Sem_5 );
                    Log.e ( TAG , "onClick: vStudent_Sem_6 : "+vStudent_Sem_6 );
                    Log.e ( TAG , "onClick: vStudent_Sem_OverAll : "+vStudent_Sem_OverAll );
                    orders_list_fragment.setArguments(bundle_cate);
                    AppCompatActivity activity = (AppCompatActivity) context;
                    activity.getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container, orders_list_fragment).
                            addToBackStack(null).commit();activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, orders_list_fragment).addToBackStack(null).commit();
                }
            });

            edit.setOnClickListener(v -> {
                EditFragment editFragment = new EditFragment ();
                Bundle bundle = new Bundle();
                bundle.putString("vStudent_ID", vStudent_ID);
                bundle.putString("vStudent_Name", vStudent_Name);
                bundle.putString("vStudent_RegNumber", vStudent_RegNumber);
                bundle.putString("vStudent_Number", vStudent_Number);
                bundle.putString("vStudent_Email", vStudent_Email);
                bundle.putString("vStudent_Aadher", vStudent_Aadher);
                bundle.putString("vStudent_DOB", vStudent_DOB);
                bundle.putString("vStudent_Address", vStudent_Address);
                bundle.putString("vStudent_Education", vStudent_Education);
                bundle.putString("vStudent_10_Marks", vStudent_10_Marks);
                bundle.putString("vStudent_10_Percentage", vStudent_10_Percentage);
                bundle.putString("vStudent_12_Marks", vStudent_12_Marks);
                bundle.putString("vStudent_12_Percentage", vStudent_12_Percentage);
                bundle.putString("vStudent_Sem_1", vStudent_Sem_1);
                bundle.putString("vStudent_Sem_2", vStudent_Sem_2);
                bundle.putString("vStudent_Sem_3", vStudent_Sem_3);
                bundle.putString("vStudent_Sem_4", vStudent_Sem_4);
                bundle.putString("vStudent_Sem_5", vStudent_Sem_5);
                bundle.putString("vStudent_Sem_6", vStudent_Sem_6);
                bundle.putString("vStudent_Sem_OverAll", vStudent_Sem_OverAll);
                bundle.putString("vUser_Id", post.getvUser_Id());
                editFragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) context;
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, editFragment)
                        .addToBackStack(null)
                        .commit();
            });

            Product_Delete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    showDeleteConfirmationDialog(position);
                }
            });
        }
    }
    private void showDeleteConfirmationDialog(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Student")
                .setMessage("Are you sure you want to delete this student?")
                .setPositiveButton("Yes", (dialog, which) -> removeItem(position))
                .setNegativeButton("No", null)
                .show();
    }

    private void removeItem(int position) {
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.Delete_Student_List(
                posts.get(position).getvStudent_Name()
               );
        posts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, posts.size());

    }
}



