package com.example.studentapp.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.studentapp.Activity.HomeActivity;
import com.example.studentapp.Activity.LoginActivity;
import com.example.studentapp.Pref.Pref;
import com.example.studentapp.R;

public class SettingFragment extends Fragment {

    private ImageView back;
    private RelativeLayout accountInfo, admin, Check ;
    private LinearLayout save, cancel;
    private Dialog dialog;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        setStatusBarColor();
        initializeViews(view);
        setClickEvents(view);
        return view;
    }

    private void setStatusBarColor() {
        Window window = requireActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.purple));
    }

    private void initializeViews(View view) {
        back = view.findViewById(R.id.back);
        accountInfo = view.findViewById(R.id.Account_Info);
        admin = view.findViewById(R.id.admin);
        Check = view.findViewById(R.id.Check);


        // Initialize dialog
        dialog = new Dialog(requireContext(), R.style.CustomDialogTheme);
    }

    private void setClickEvents(View view) {

        accountInfo.setOnClickListener(v -> navigateToFragment(new StorageFragment ()));
        back.setOnClickListener(v -> navigateToFragment(new DashboardFragment ()));
        admin.setOnClickListener(v -> navigateToFragment(new admin_Fragment()));
        view.findViewById(R.id.Logout_bt).setOnClickListener(v -> showExitPopup());
        Check.setOnClickListener(v -> navigateToFragment(new CheckFragment()));

    }

    private void navigateToHome() {
        Intent intent = new Intent(getActivity(), HomeActivity.class); // Navigates to HomeActivity
        startActivity(intent);
    }

    private void navigateToFragment(Fragment fragment) {
        AppCompatActivity activity = (AppCompatActivity) requireContext();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void showExitPopup() {
        dialog.setContentView(R.layout.fragment_log_out_); // Correctly inflates the popup_exit layout
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        cancel = dialog.findViewById(R.id.cancel);
        save = dialog.findViewById(R.id.save);

        dialog.show();

        cancel.setOnClickListener(v -> dialog.dismiss());
        save.setOnClickListener(v -> performLogout());
    }

    private void performLogout() {
        Pref.removeAll(getContext()); // Clear all saved preferences

        Intent intent = new Intent(getContext(), LoginActivity.class); // Redirects to LoginActivity
        startActivity(intent);
        requireActivity().finish(); // Ends the current activity
        dialog.dismiss();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                } else {
                    navigateToHome();
                }
            }
        });
    }
}