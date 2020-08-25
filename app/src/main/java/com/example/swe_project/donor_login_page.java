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

public class donor_login_page extends AppCompatActivity {
    private EditText emailid;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_login_page);
        emailid = findViewById(R.id.emailid);
        password = findViewById(R.id.password);

    }
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void checkcredentials(View view) {
        String getemailid = emailid.getText().toString();
        if (!isEmailValid(getemailid)){
            Toast.makeText(this,"Invalid Email ID",Toast.LENGTH_SHORT).show();
            //return;
        }
        String url = "http://192.168.1.2:8000/auth/login/donor";
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("password",password.getText().toString());
        params.put("email",getemailid);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String token = response.getString("token");
                    String id = response.getString("userId");
                    Log.d("Reponse",token);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.putString("id",id);
                    boolean commit = editor.commit();
                    Intent intent = new Intent(donor_login_page.this,UserActivity.class);
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
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}