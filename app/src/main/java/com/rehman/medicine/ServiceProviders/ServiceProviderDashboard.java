package com.rehman.medicine.ServiceProviders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.rehman.medicine.Common.SigninSignup.OrderActivity;
import com.rehman.medicine.Common.WelcomeScreen;
import com.rehman.medicine.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rehman.medicine.shopkeeper.UploadProductsActivity;

public class ServiceProviderDashboard extends AppCompatActivity {
 Button Order,uploadData,Profile;
 String job;
 ImageView Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_dashboard);

        Order=findViewById(R.id.check_order_btn);
        uploadData=findViewById(R.id.uploadData);
        Logout=findViewById(R.id.service_man_logout_button);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent LogOutIntent = new Intent(getApplicationContext(), WelcomeScreen.class);
                startActivity(LogOutIntent);
            }
        });
        SharedPreferences sharedpreferences = getSharedPreferences("saveLoging", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("login","shopkeeper");
        editor.apply();






                 uploadData.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                          Intent intent=new Intent(getApplicationContext(), UploadProductsActivity.class);
                          startActivity(intent);
                     }
                 });
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceProviderDashboard.this, OrderActivity.class);

                startActivity(intent);
            }
        });




    }
}