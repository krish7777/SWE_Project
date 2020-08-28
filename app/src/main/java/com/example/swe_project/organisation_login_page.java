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

public class organisation_login_page extends AppCompatActivity {
    private EditText emailView;
    private EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_login_page);
        emailView= findViewById(R.id.login_org_email);
        passwordView= findViewById(R.id.login_org_password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(AuthChecker.authChecker(this)==true){
            Log.d("Res","haha already exist going");
            Intent intent = new Intent(organisation_login_page.this, UserActivity.class);
            startActivity(intent);
        }
    }

    public boolean validateCredentials(String email, String password){
        if(password.length()==0)
            return false;
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()==true)
            return true;
        return false;
    }

    public void loginOrganisation(View view) {
        String email= emailView.getText().toString();
        String password= passwordView.getText().toString();
        if(validateCredentials(email, password)== true){
            String url = "http://192.168.1.7:8000/auth/login/organisation";
            final HashMap<String, String> params = new HashMap<String, String>();
            params.put("password",password);
            params.put("email",email);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Res","evercame");
                    try {
                        Log.d("Result",response.toString());
                        String token = response.getString("token");
                        String id = response.getString("userId");
                        Log.d("Reponse",token);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", token);
                        editor.putString("id",id);
                        boolean commit = editor.commit();
                        Log.d("Res","about to go");
                        Intent intent = new Intent(organisation_login_page.this, UserActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {

                        e.printStackTrace();
                        Log.d("Response", "error");
                        Toast.makeText(getApplicationContext(),"Some Error Occured. Please Try Again", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Response error", String.valueOf(error));
                    Toast.makeText(getApplicationContext(),"Some Error Occured. Please Try Again", Toast.LENGTH_LONG).show();
                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        }else{
            Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
        }
    }
}