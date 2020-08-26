package com.example.swe_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "testing git here");
       // getActionBar().hide();
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(AuthChecker.authChecker(this)==true){
            Intent intent = new Intent(this,UserActivity.class);
            startActivity(intent);
        }
    }

    public void donorlogin(View view) {
        Intent intent = new Intent(this,donor_login_page.class);
        startActivity(intent);
    }

    public void organisationlogin(View view){
        Intent intent= new Intent(this, organisation_login_page.class);
        startActivity(intent);
    }

    public void openRegistrationHome(View view){
        Intent intent =new Intent(this,RegistrationHomeActivity.class);
        startActivity(intent);
    }


}

