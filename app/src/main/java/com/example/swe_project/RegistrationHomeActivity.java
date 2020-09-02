package com.example.swe_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistrationHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_home);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String role =AuthChecker.authChecker(this);
        if(role.length()!=0){
            Intent intent;
            if(role.equals("Donor")){
                intent =new Intent(this,UserActivity.class);
            }
            else{
                intent = new Intent(this,OrgActivity.class);
            }
            startActivity(intent);
        }
    }

    public void openDonorRegistration(View view) {
        Intent intent = new Intent(this,donor_registration_page.class);
        startActivity(intent);
    }

    public void openOrganisationRegistration(View view){
        Intent intent= new Intent(this, organisation_registration_page.class);
        startActivity(intent);
    }

}