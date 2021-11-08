package com.rehman.medicine.NavigationDrawer.ui.MyProfile;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rehman.medicine.HelperClasses.UserHelperClass;
import com.rehman.medicine.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileFragment extends Fragment {

    //Variables
    TextInputLayout fullName, email, mobileNo,address;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mRef;

    String Tag="MyTag";

    FirebaseUser firebaseUser;
    String userId;
    private MyProfileViewModel mViewModel;
    ProgressDialog progressDialog;


    public static MyProfileFragment newInstance() {
        return new MyProfileFragment();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);
       firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
       userId=firebaseUser.getUid();


        //Hocks
        fullName = root.findViewById(R.id.full_name_profile);
        email = root.findViewById(R.id.email_profile);
        mobileNo = root.findViewById(R.id.mobile_profile);
        address=root.findViewById(R.id.address);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Profile");
        progressDialog.setMessage("wait!\n While we access You Profile");
        progressDialog.show();
        firebaseDatabase= FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getInstance().getReference().child("Users");
        mRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserHelperClass user= dataSnapshot.getValue(UserHelperClass.class);
                fullName.getEditText().setText(user.getFullName());
                email.getEditText().setText(user.getEmail());
                mobileNo.getEditText().setText(user.getPhoneNo());
                address.getEditText().setText(user.getAddress());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),""+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyProfileViewModel.class);

        // TODO: Use the ViewModel
    }
}