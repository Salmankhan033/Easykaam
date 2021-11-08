 package com.rehman.medicine.Common.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rehman.medicine.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {
    ImageView backBtn2;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;
    Button next;
    String fullName, email, address,job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backBtn2 = findViewById(R.id.signIn_back_button);
        next = findViewById(R.id.login_next_button);
        phoneNumber = findViewById(R.id.signin_mobile_number);
        countryCodePicker = findViewById(R.id.Lcountry_code_picker);

        //This button is for back action to 1st signUp screen
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginOption.class);
                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(backBtn2, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });




        //This button is for getting OTP code and moving to next OTP activity
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                //Get all values from previous screen "SignUp" using intent
//                fullName = getIntent().getStringExtra("fullName");
//                email = getIntent().getStringExtra("email");
//                address = getIntent().getStringExtra("address");
               job=getIntent().getStringExtra("SelectJob");
//                String SelectedJob=getIntent().getStringExtra("SelectJob");





                String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim(); // Get Phone Number
                String phoneNo = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;  // Get both country code and Phone number
                Intent intent = new Intent(getApplicationContext(), SinInOTPActivity.class);

                //Pass all fields data to next activity
              //  intent.putExtra("fullName", fullName);
              //  intent.putExtra("email", email);
              //  intent.putExtra("address", address);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("Job",job);
              //  intent.putExtra("SelectJob",SelectedJob);

                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(next, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {

                    startActivity(intent);
                }
            }
        });
    }


}
