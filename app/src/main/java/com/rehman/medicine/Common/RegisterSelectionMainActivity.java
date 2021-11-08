package com.rehman.medicine.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.rehman.medicine.ServiceProviders.ServiceProviderDashboard;
import com.rehman.medicine.NavigationDrawer.NavigationDrawerActivity;
import com.rehman.medicine.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterSelectionMainActivity extends AppCompatActivity {
    Button DoneBtn;
    TextInputLayout Job;
    Spinner spinner;
    String Tag="MyTag";
     String arry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_selection_main);
        spinner=findViewById(R.id.Rjob);
        Job= findViewById(R.id.rspon);
        DoneBtn=findViewById(R.id.doneBtn);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Sjob[]=getResources().getStringArray(R.array.spin_array);
                 arry=Sjob[position];
                Job.getEditText().setText(Sjob[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        DoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String job=Job.getEditText().getText().toString();

                if(job.isEmpty()){
                    Job.setError("Please Select Your Job From Spinner");

                }
                else if (job.equals("Customer")){
                    Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);

                    startActivity(intent);


                }
                else {

                  Intent intent=new Intent(getApplicationContext(), ServiceProviderDashboard.class);
                  startActivity(intent);

                }
            }
        });
    }
}
