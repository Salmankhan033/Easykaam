package com.rehman.medicine.Common.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.rehman.medicine.Common.WelcomeScreen;
import com.rehman.medicine.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginOption extends AppCompatActivity {
    ImageView backbutton;
    Button NextBtn, loginToSignupButton;
    TextInputEditText Job;
Spinner spinner;

  String register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);
        backbutton = findViewById(R.id.login_option_back_button);
        loginToSignupButton = findViewById(R.id.login_to_signup_button);
        spinner=findViewById(R.id.job);
        Job= findViewById(R.id.inputJob);
        NextBtn=findViewById(R.id.next);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOption.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });

        loginToSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOption.this, SignUp.class);
                startActivity(intent);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
     @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         String Sjob[]=getResources().getStringArray(R.array.spin_array);

         Job.setText(Sjob[position]);

     }

     @Override
     public void onNothingSelected(AdapterView<?> parent) {


     }
 });
        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job= Job.getText().toString();
                if(job.isEmpty()){
                    Job.setError("Please Select Your Job From Spinner");
                }else {
                        Intent intent = new Intent(LoginOption.this, Login.class);
                        intent.putExtra("SelectJob", job);
                        startActivity(intent);

                }
            }
        });

    }

}