package com.example.studentapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.R;


public class AnimationActivity extends AppCompatActivity {

    private ImageView animatedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        animatedImage = findViewById(R.id.animatedImage);

        // Load animation resource
        Animation blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);
        animatedImage.startAnimation(blinkAnimation);

        // Delay for 5 seconds before moving to the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navigate to the next activity (e.g., HomeActivity)
                Intent intent = new Intent(AnimationActivity.this, GetStartedActivity.class);
                startActivity(intent);
                finish(); // Finish this activity
            }
        }, 1000);
    }
}
