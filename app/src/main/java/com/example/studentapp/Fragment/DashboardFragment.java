package com.example.studentapp.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.Pref.Pref;
import com.example.studentapp.R;
import com.google.android.material.navigation.NavigationView;


public class DashboardFragment extends Fragment {
    private TextView User_Name_Txt;
    private DBHelper dbHelper;
    private SearchView searchView;
    private String iUser_Id, vUser_Name;
    private LinearLayout Add_Student_ly, Student_List_ly, cgpa;
    private ImageView menu, voice;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    public DashboardFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_dashboard , container , false );
        setStatusBarColor();
        initializeViews ( view );
        loadUserName ( );
        setClickListeners ( );
        setupSideNavBar(view);


        return view;
    }

    private void setStatusBarColor() {
        Window window = requireActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.purple));
    }


    private void setupSideNavBar(View view) {
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.navigation_view);
        toolbar = view.findViewById(R.id.toolbar);



        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }


        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle Toolbar Click to Open/Close Drawer
        toolbar.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (item.getItemId() == R.id.nav_add_student) {
                selectedFragment = new Add_Student_Fragment();
            } else if (item.getItemId() == R.id.nav_list) {
                selectedFragment = new Student_List_Fragment();
            } else if (item.getItemId() == R.id.nav_cgpa) {
                selectedFragment = new CGPAFragment();
            } else if (item.getItemId() == R.id.nav_note) {
                selectedFragment = new NotesFragment();
            } else if (item.getItemId() == R.id.nav_image) {
                selectedFragment = new ImageFragment();
            } else if (item.getItemId() == R.id.nav_settings) {
                selectedFragment = new SettingFragment();
            }

            if (selectedFragment != null) {
                navigateToFragment(selectedFragment);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    private void initializeViews(View view) {
        User_Name_Txt = view.findViewById ( R.id.User_Name_Txt );
        Add_Student_ly = view.findViewById ( R.id.Add_Student_ly );
        Student_List_ly = view.findViewById ( R.id.Student_List_ly );
        cgpa = view.findViewById ( R.id.cgpa );
        searchView = view.findViewById ( R.id.searchView );
    }

    private void loadUserName() {
        iUser_Id = Pref.get_iUser_id ( getContext ( ) );
        dbHelper = new DBHelper ( getContext ( ) );

        try (Cursor cursor = dbHelper.get_vUsername_Password_from_Usertable ( Integer.parseInt ( iUser_Id ) )) {
            if (cursor != null && cursor.moveToFirst ( )) {
                vUser_Name = cursor.getString ( 0 );
                User_Name_Txt.setText ( vUser_Name );
            } else {
                User_Name_Txt.setText ( "User" );
            }
        } catch (Exception e) {
            e.printStackTrace ( );
            User_Name_Txt.setText ( "Error" );
        }
    }

    private void setClickListeners() {
        Add_Student_ly.setOnClickListener ( v -> navigateToFragment ( new Add_Student_Fragment ( ) ) );
        Student_List_ly.setOnClickListener ( v -> navigateToFragment ( new Student_List_Fragment ( ) ) );
        cgpa.setOnClickListener ( v -> navigateToFragment ( new CGPAFragment ( ) ) );
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }
}


