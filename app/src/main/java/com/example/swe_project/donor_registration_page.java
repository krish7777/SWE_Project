package com.example.swe_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;

public class donor_registration_page extends AppCompatActivity {

    private EditText emailView;
    private EditText passwordView;
    private EditText nameView;
    private EditText contactNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration_page);
        emailView = findViewById(R.id.donor_reg_email);
        passwordView = findViewById(R.id.donor_reg_password);
        nameView = findViewById(R.id.donor_reg_name);
        contactNumberView = findViewById(R.id.donor_reg_contactNumber);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (AuthChecker.authChecker(this) == true) {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        }
    }

    public boolean validateCredentials(String email, String password, String name, String contactNumber) {
        if(name.length() ==0)
            return false;
        if (password.length() == 0)
            return false;
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
            return false;
        if (Patterns.PHONE.matcher(contactNumber).matches() == false)
            return false;
        return true;
    }

    public void registerDonor(View view) {
        String url = "http://192.168.1.5:8000/auth/register/donor";
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String name = nameView.getText().toString();
        String contactNumber = contactNumberView.getText().toString();
        if (validateCredentials(email, password,name, contactNumber) == true) {

            final HashMap<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("password", password);
            params.put("name", name);
            params.put("contactNumber", contactNumber);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Response", response.toString());
                        Intent intent = new Intent(donor_registration_page.this,donor_login_page.class);
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
                    Toast.makeText(getApplicationContext(), "Error in Registration. Please Try Again", Toast.LENGTH_SHORT).show();
                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        } else {
            Toast.makeText(getApplicationContext(), "Please fill all fields correctly!", Toast.LENGTH_SHORT).show();
        }
    }
}