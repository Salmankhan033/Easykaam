package com.rehman.medicine.Common.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rehman.medicine.Common.WelcomeScreen;
import com.rehman.medicine.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    //Variables
    TextInputLayout regName, regEmail, regAddress;
    ImageView backBtn;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hocks
        regName = findViewById(R.id.reg_fullName);
        regEmail = findViewById(R.id.reg_email);
        regAddress = findViewById(R.id.reg_address);
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Call the validate functions
                if (!validateFullName() | !validateEmail() | !validatePassword()){
                    return;
                }


                Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);

                //Get all field data from their Text boxes
                String _fullName = regName.getEditText().getText().toString();
                String _email = regEmail.getEditText().getText().toString();
                String _password = regAddress.getEditText().getText().toString();


                //Pass all fields data to the next activity "Signup2ndClass"
                intent.putExtra("fullName", _fullName);
                intent.putExtra("email", _email);
                intent.putExtra("address", _password);



                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(next, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }


            }
        });



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);

                //Add Transition
                Pair[] pairs = new Pair[1];

                pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                    startActivity(intent, options.toBundle());

                } else {
                   startActivity(intent);
                }
            }
        });

    }

    /*
    Validation Functions
     */

    //Validation Full Name Field
    private boolean validateFullName() {
        String fullName_value = regName.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            regName.setError("Field can not be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    //Validation Email Field
    private boolean validateEmail() {
        String email_value = regEmail.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email_value.isEmpty()) {
            regEmail.setError("Field can not be empty");
            return false;
        } else if (!email_value.matches(checkEmail)) {
            regEmail.setError("Invalid Email");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    //Validation Password Field
    private boolean validatePassword() {
        String password_value = regAddress.getEditText().getText().toString().trim();
//        String checkPassword = "^" +
//                "(?=.*[0-9])"+                   // at least 1 digit
//                 "(?=.*[a-z])" +               // at least 1 lower case letter
//                "(?=.*[A-Z])" +               // at least 1 Uper case letter
//                "(?=.*[a-zA-Z])" +             //Any Letter
//                "(?=.*[@#$%^&+=])" +       // At least 1 special character
//                "(?=\\s+$)" +           // no white spaces
//               ".{4,}" +                //at least 4 characters
//               "$";

        if (password_value.isEmpty()) {
            regAddress.setError("Field can not be empty");
            return false;

//        } else if (!password_value.matches(checkPassword)) {
//            regPassword.setError("Password should contain 4 characters!");
//            return false;

        } else {
            regAddress.setError(null);
            regAddress.setErrorEnabled(false);
            return true;
        }
    }
}


