package com.example.studentapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.studentapp.Pref.Pref;
import com.example.studentapp.R;

public class SplashActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 1000;
    String iUser_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);

        main_function();
    }

    public void main_function(){

        new Handler ().postDelayed(new Runnable() {
            @Override
            public void run() {
                iUser_Id = Pref.get_iUser_id(getApplicationContext());

                Log.e ( "TAG" , "run: iUser_Id1 : "+iUser_Id );

                if(iUser_Id == null){
                    startActivity(new Intent (SplashActivity.this, LoginActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}