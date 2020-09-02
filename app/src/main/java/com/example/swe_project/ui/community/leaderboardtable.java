package com.example.swe_project.ui.community;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.swe_project.R;

public class leaderboardtable extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboardtable);
        getSupportActionBar().setTitle("Leader Board");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
}