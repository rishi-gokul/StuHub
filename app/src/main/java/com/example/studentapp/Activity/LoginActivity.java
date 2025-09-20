package com.example.studentapp.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.Config.CustomToast;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.Pref.Pref;
import com.example.studentapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton;
    private TextView signup;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(getApplicationContext());

        initViews();
        setListeners();
    }

    private void initViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signup);
    }

    private void setListeners() {
        signup.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });

        loginButton.setOnClickListener(view -> {
            String usernameText = username.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            if (usernameText.isEmpty() || passwordText.isEmpty()) {
                CustomToast.show(getApplicationContext(), "Please enter username and password", R.drawable.warning, true);
                return;
            }

            if (dbHelper.check_username_UserTable(usernameText)) {
                int userId = dbHelper.check_username_password_UserTable(usernameText);
                if (userId == 0) {
                    CustomToast.show(getApplicationContext(), "Invalid Username or Password", R.drawable.warning, true);
                } else {
                    validateUser(userId, usernameText, passwordText);
                }
            } else {
                CustomToast.show(getApplicationContext(), "Invalid Username or Password", R.drawable.warning, true);
            }
        });
    }

    private void validateUser(int userId, String inputUsername, String inputPassword) {
        try (Cursor cursor = dbHelper.get_vUsername_Password_from_Usertable(userId)) {
            if (cursor.moveToFirst()) {
                String storedUsername = cursor.getString(0);
                String storedPassword = cursor.getString(1);

                Log.e("LoginActivity", "DB Username: " + storedUsername + " | DB Password: " + storedPassword);
                Log.e("LoginActivity", "Input Username: " + inputUsername + " | Input Password: " + inputPassword);

                if (inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword)) {
                    Pref.put_iUser_id(getApplicationContext(), String.valueOf(userId));
                    CustomToast.show(getApplicationContext(), "Login Successful", R.drawable.save_gray, true);
                    startActivity(new Intent(LoginActivity.this, AnimationActivity.class));
                } else {
                    CustomToast.show(getApplicationContext(), "Invalid Username or Password", R.drawable.warning, true);
                }
            } else {
                CustomToast.show(getApplicationContext(), "Invalid Username or Password", R.drawable.warning, true);
            }
        }
    }
}
