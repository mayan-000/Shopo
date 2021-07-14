package com.example.shopo.ExtraActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.shopo.MainActivities.MainActivity;
import com.example.shopo.R;

public class splash_screen extends AppCompatActivity {

    ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        icon = findViewById(R.id.animate);

        Animation splash = AnimationUtils.loadAnimation(this,R.anim.spalsh_icon);

        icon.setAnimation(splash);

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(splash_screen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();


    }
}