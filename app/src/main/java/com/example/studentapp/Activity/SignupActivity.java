package com.example.studentapp.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignupActivity extends AppCompatActivity {

    private EditText fullName, email, password, confirmPassword;
    private Button signupButton;
    private TextView loginText;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Views
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signupButton = findViewById(R.id.loginButton);
        loginText = findViewById(R.id.loginText);

        dbHelper = new DBHelper (getApplicationContext());

        // Set OnClickListener for Sign Up Button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameText = fullName.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                String confirmPasswordText = confirmPassword.getText().toString().trim();

                Log.e ( TAG , "onClick: fullNameText : " +fullNameText );
                Log.e ( TAG , "onClick: emailText : " +emailText );
                Log.e ( TAG , "onClick: passwordText : " +passwordText );
                Log.e ( TAG , "onClick: confirmPasswordText : " +confirmPasswordText );

                // Validate Inputs
                if (TextUtils.isEmpty(fullNameText)) {
                    fullName.setError("Full Name is required");
                    return;
                }

                if (TextUtils.isEmpty(emailText)) {
                    email.setError("Email is required");
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    email.setError("Enter a valid email address");
                    return;
                }

                if (TextUtils.isEmpty(passwordText)) {
                    password.setError("Password is required");
                    return;
                }

                if (passwordText.length() < 6) {
                    password.setError("Password must be at least 6 characters");
                    return;
                }

                if (!passwordText.equals(confirmPasswordText)) {
                    confirmPassword.setError("Passwords do not match");
                    return;
                }

                Boolean uName = dbHelper.check_username_UserTable(fullNameText);

                Log.e ( TAG , "onClick: uName : "+uName );

                if (uName == false) {
                    boolean inserted1 = dbHelper.InsertUsers(fullNameText,emailText,passwordText);

                    Integer Us_id = dbHelper.check_username_password_UserTable(fullNameText);
                    Log.e(TAG, "onClick: uName : " + Us_id);

                    if (Us_id == 0) {
                        CustomToast.show(getApplicationContext (), "Invalid UserName", R.drawable.warning,true);
                        Log.e(TAG, "onClick: uName : IF " + Us_id);
                    }
                    else {
                        Pref.put_iUser_id (getApplicationContext(), String.valueOf (Us_id));
                        CustomToast.show(getApplicationContext(), "Login Successful", R.drawable.save_gray,true);

                        Intent intent = new Intent (SignupActivity.this , AnimationActivity.class);
                        startActivity (intent);
                        finish ( );
                    }

                }else{

                    CustomToast.show(getApplicationContext (), "User Already Exist", R.drawable.warning,true);

                }
            }
        });

        // Set OnClickListener for Login Text
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Login Page
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
