package com.example.swe_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

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

        if(AuthChecker.authChecker(this)==true){

            String url = "http://192.168.1.4:8000/auth/isAuth";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Response here", response.toString());
                        Intent intent = new Intent(MainActivity.this,UserActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("Response", "error");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Response error", String.valueOf(error));
                    AuthChecker.logout(MainActivity.this);
                }
            }){
                @Override
                public Map<String, String> getHeaders()
                {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String token = sharedPreferences.getString("token","");
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization",token);
                    return headers;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        }

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

