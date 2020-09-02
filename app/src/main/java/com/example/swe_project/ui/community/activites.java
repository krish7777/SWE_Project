package com.example.swe_project.ui.community;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.swe_project.R;

public class activites extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activites_activity);
        getSupportActionBar().setTitle("Activity page");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
/*
        activitesFragment activity = new activitesFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, activity);
        transaction.commit();
        */
    }
}