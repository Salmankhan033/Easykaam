package com.rehman.medicine.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.rehman.medicine.Common.SigninSignup.LoginOption;
import com.rehman.medicine.Common.SigninSignup.SignUp;
import com.rehman.medicine.NavigationDrawer.NavigationDrawerActivity;
import com.rehman.medicine.R;
import com.google.firebase.auth.FirebaseAuth;
import com.rehman.medicine.ServiceProviders.ServiceProviderDashboard;

public class WelcomeScreen extends AppCompatActivity {

    Button loginBtn, RegisterBtn;
    FirebaseAuth FBAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);

        loginBtn = findViewById(R.id.login_btn_wlcmSc);
        RegisterBtn = findViewById(R.id.register_btn_wlcmSc);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WelcomeScreen.this, LoginOption.class);

                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(loginBtn, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeScreen.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WelcomeScreen.this, SignUp.class);

                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(RegisterBtn, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeScreen.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            SharedPreferences sharedpreferences = getSharedPreferences("saveLoging", Context.MODE_PRIVATE);
            String logIn=sharedpreferences.getString("login", String.valueOf(MODE_PRIVATE));

            if(logIn.equals("customer")){
            Intent intent = new Intent(WelcomeScreen.this, NavigationDrawerActivity.class);
            startActivity(intent);
            finish();
            }
            else {
                Intent intent = new Intent(WelcomeScreen.this, ServiceProviderDashboard.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
