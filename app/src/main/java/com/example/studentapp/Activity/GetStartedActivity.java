package com.example.studentapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.R;

public class GetStartedActivity extends AppCompatActivity {

    Button loginButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        // Initialize views and set up click events
        FindID_View();
        Click_Event();
    }

    private void FindID_View() {
        loginButton1 = findViewById(R.id.loginButton1);
    }


    private void Click_Event() {
        loginButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GetStartedActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

