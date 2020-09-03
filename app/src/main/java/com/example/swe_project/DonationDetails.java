package com.example.swe_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.swe_project.ui.discover.DiscoverAdapter;
import com.example.swe_project.ui.discover.DiscoverData;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DonationDetails extends AppCompatActivity {

    //Button button;
    EditText details;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donationdetails);
        getSupportActionBar().setTitle("DETAILS FOR DONATION");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        details = findViewById(R.id.editTextTextMultiLine);

    }

    public void donations(View view){

            Toast.makeText(getBaseContext(), "Donation Registered" , Toast.LENGTH_SHORT ).show();

            String url = getString(R.string.url)+"/donor/makedonation";
            final HashMap<String, String> params = new HashMap<String, String>();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String id = sharedPreferences.getString("id","");
            LatLng reqLocation= getIntent().getExtras().getParcelable(MapsActivity.mapReply);
            params.put("id",id);
            params.put("description",details.getText().toString());
            params.put("latitude",Double.toString(reqLocation.latitude));
            params.put("longitude",Double.toString(reqLocation.latitude));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Res","evercame");
                    try {

                    } catch (Exception e) {

                        e.printStackTrace();
                        Log.d("Response", "inside error");
                        // Toast.makeText(getApplicationContext(),"Some Error Occured. Please Try Again", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Response error", String.valueOf(error));
                    // AuthChecker.logout(MainActivity.this);
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
            openActivity();

        }

    public void openActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}