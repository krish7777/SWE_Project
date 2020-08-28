package com.example.swe_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DonationDetails extends AppCompatActivity {

    Button button;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donationdetails);
        getSupportActionBar().setTitle("DETAILS FOR DONATION");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();

            }
        });
    }

    private void openActivity() {

        Intent intent = new Intent(this, DiscoverMapsActivity.class);
        startActivity(intent);

    }
}