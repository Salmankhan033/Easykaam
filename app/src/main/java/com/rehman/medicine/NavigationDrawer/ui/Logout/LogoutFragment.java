package com.rehman.medicine.NavigationDrawer.ui.Logout;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rehman.medicine.Common.WelcomeScreen;
import com.rehman.medicine.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FirebaseAuth.getInstance().signOut();
        Intent LogOutIntent = new Intent(getContext(), WelcomeScreen.class);
        startActivity(LogOutIntent);

        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LogoutViewModel.class);
        // TODO: Use the ViewModel
    }

}