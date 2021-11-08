package com.rehman.medicine.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rehman.medicine.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 2000;


        //Variables
            ImageView background_logo;

        //Animation_Variables
            Animation logoAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hocks_Variables
        background_logo = findViewById(R.id.splash_logo);

        //Hocks_Animations
        logoAnim = AnimationUtils.loadAnimation(this,R.anim.left_side_anim);

        //Set Animations on elements
        background_logo.setAnimation(logoAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                startActivity(intent);
                finish();


            }
        },SPLASH_TIMER);

    }

}
