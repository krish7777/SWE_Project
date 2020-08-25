package com.example.swe_project;

import androidx.appcompat.app.AppCompatActivity;

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

public class organisation_registration_page extends AppCompatActivity {

    private EditText email;
    private  EditText password;
    private EditText name;
    private EditText contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_registration_page);

        email =   findViewById(R.id.organisation_reg_email);
        password = findViewById(R.id.organisation_reg_password);
        name = findViewById(R.id.organisation_reg_name);
        contactNumber = findViewById(R.id.organisation_reg_contactNumber);
    }

    public void registerOrganisation(View view) {
        String url = "http://192.168.1.2:8000/auth/register/organisation";
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("email",email.getText().toString());
        params.put("password",password.getText().toString());
        params.put("name",name.getText().toString());
        params.put("contactNumber",contactNumber.getText().toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String yes = response.getString("yes");
                    Log.d("Response", yes);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Response", "error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error", String.valueOf(error));
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}